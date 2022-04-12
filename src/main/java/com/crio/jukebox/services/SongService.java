package com.crio.jukebox.services;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.interfaces.IPlayListRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.interfaces.ISongService;

public class SongService implements ISongService {

    private final ISongRepository songRepository;
    private final IUserRepository userRepository;
    private final IPlayListRepository playListRepository;

    public SongService(ISongRepository songRepository, IUserRepository userRepository, IPlayListRepository playListRepository) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playListRepository = playListRepository;
    }
 
    @Override
    public void loadData(File inputFile) {

        String line = "";  
        String splitBy = ",";  
        try   
        {  
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader(inputFile));  
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
                String[] song = line.split(splitBy);    // use comma as separator  
                System.out.println("Song [Name=" + song[0] + ", Genre=" + song[1] + ", Album=" + song[2] + ", MainArtist=" + song[3] + ", ListOfArtists= " + song[4] +"]");
                List<String> artistList = new ArrayList<>(Arrays.asList(song[4].split("#")));
                Song songObj = new Song(song[0], song[1], song[2], song[3], artistList);
                songRepository.save(songObj);
            }
            br.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  

    }

    @Override
    public Song play(String userID, String operation) throws Exception{

        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User Not Found."));
        String currentPlaylistID = user.getCurrentPlaylistId();
        Playlist activePlaylist = playListRepository.findById(currentPlaylistID).orElseThrow(() -> new RuntimeException("Playlist Not Found."));
        List<Song> songs = activePlaylist.getSongs();
        int length = songs.size();
        String prevSongID = activePlaylist.getCurrentSongID();
        String newSongID = "";

        if(operation == "NEXT") {
            for(int i = 0; i < length; i++) {
                Song song = songs.get(i);

                if(song.getId() == prevSongID) {
                    newSongID = songs.get((i+1) % length).getId();
                }   
            }
        }
        else if(operation == "BACK") {
            for(int i = 0; i < length; i++) {
                Song song = songs.get(i);

                if(song.getId() == prevSongID) {
                    newSongID = songs.get((i-1 + length) % length).getId();
                }   
            }
        }
        else {
            newSongID = operation;
        }

        Song newSong = songRepository.findById(newSongID).orElseThrow(() -> new RuntimeException("Song Not Found in the current active playlist."));
        activePlaylist.setCurrentSongID(newSongID);
        playListRepository.save(activePlaylist);
        return newSong;
    }
}