package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.interfaces.IPlayListRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.interfaces.IPlayListService;

public class PlayListService implements IPlayListService {
    private final IPlayListRepository playListRepository;
    private final IUserRepository userRepository;
    private final ISongRepository songRepository;

    public PlayListService(IPlayListRepository playListRepository, IUserRepository userRepository, ISongRepository songRepository) {
        this.playListRepository = playListRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    private List<Song> validateAddListOfSongs(List<String> songIDs, List<Song> songList) throws Exception {
        List<Song> newSongList = songList;
        for(String id : songIDs) {
            Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Some Requested Songs Not Available. Please try again."));
            newSongList.add(song);
        }
        return newSongList;
    }

    private List<Song> validateDeleteListOfSongs(List<String> songIDs, List<Song> songList) throws Exception {
        Map<String, Song> presentSongs = new HashMap<>();

        for(Song song : songList) {
            presentSongs.put(song.getId(), song);
        }
        for(String id : songIDs) {
            Song returnValue = presentSongs.remove(id);
            if(returnValue == null) {
                throw new RuntimeException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }
        return new ArrayList<>(presentSongs.values());
    }

    @Override
    public Playlist create(String userID, String playlistName, List<String> songIDs) throws Exception {
        final User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User Not Found."));
        List<Song> songs = validateAddListOfSongs(songIDs, new ArrayList<>());
        Playlist playlist = playListRepository.save(new Playlist(playlistName, songs, user));
        user.addPlayList(playlist);
        userRepository.save(user);
        return playlist;
    }

    @Override
    public void delete(String userID, String playlistID) throws Exception {
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User Not Found."));
        Playlist playlist = playListRepository.findById(playlistID).orElseThrow(() -> new RuntimeException("Playlist Not Found"));
        user.deletePlaylist(playlist);
        userRepository.save(user);
    }

    @Override
    public Playlist modify(String operation, String userID, String playlistID, List<String> songIDs) throws Exception {
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException());
        Playlist oldPlaylist = playListRepository.findById(playlistID).orElseThrow(() -> new RuntimeException());
        List<Song> songs;

        if(operation == "ADD-SONG") {
            songs = validateAddListOfSongs(songIDs, oldPlaylist.getSongs());
        }
        else {
            songs = validateDeleteListOfSongs(songIDs, oldPlaylist.getSongs());
        }

        user.deletePlaylist(oldPlaylist);
        Playlist newPlaylist = new Playlist(playlistID, oldPlaylist.getName(), songs, user);
        user.addPlayList(newPlaylist);
        userRepository.save(user);
        return playListRepository.save(newPlaylist);
    }

    @Override
    public Song play(String userID, String playlistID) throws Exception {
        User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException());
        Playlist playlist = playListRepository.findById(playlistID).orElseThrow(() -> new RuntimeException());
        List<Song> songs = playlist.getSongs();
        if(songs.size() == 0) {
            throw new RuntimeException("Playlist is empty.");
        }
        user.setCurrentPlaylistId(playlistID);
        userRepository.save(user);
        return songs.get(0);
    }

}