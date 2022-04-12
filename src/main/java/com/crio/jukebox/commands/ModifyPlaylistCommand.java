package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.interfaces.IPlayListService;

public class ModifyPlaylistCommand implements ICommand {
    private final IPlayListService playlistService;

    public ModifyPlaylistCommand(IPlayListService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String operation = tokens.get(1);
        String userID = tokens.get(2);
        String playlistID = tokens.get(3);
        List<String> songIDs = new ArrayList<>();

        for(int i = 4; i < tokens.size(); i++) {
            songIDs.add(tokens.get(i));
        }
        
        try {
            Playlist playlist = playlistService.modify(operation, userID, playlistID, songIDs);
            System.out.println("Playlist ID - " + playlist.getId());
            System.out.println("Playlist Name - " + playlist.getName());
            System.out.println("Song IDs - " + playlist.getSongs());

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}