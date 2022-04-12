package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.interfaces.IPlayListService;
import com.crio.jukebox.entities.Song;

public class RunPlaylistCommand implements ICommand {

    private final IPlayListService playlistService;

    public RunPlaylistCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userID = tokens.get(1);
        String playlistID = tokens.get(2);
        
        try {
            Song song = playlistService.play(userID, playlistID);
            System.out.println("Current Song Playing");
            System.out.println("Song  - " + song.getTitle());
            System.out.println("Album - " + song.getAlbum());
            System.out.println("Artists - " + song.getArtistList());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}