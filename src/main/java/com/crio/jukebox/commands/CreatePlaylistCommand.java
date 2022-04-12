package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.interfaces.IPlayListService;

public class CreatePlaylistCommand implements ICommand {
    private final IPlayListService playListService;

    public CreatePlaylistCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userID = tokens.get(1);
        String playlistName = tokens.get(2);
        List<String> songIDs = new ArrayList<>();

        for(int i = 3; i < tokens.size(); i++) {
            songIDs.add(tokens.get(i));
        }

        try {
            Playlist playlist = playListService.create(userID, playlistName, songIDs);
            System.out.println("Playlist ID - " + playlist.getId());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}