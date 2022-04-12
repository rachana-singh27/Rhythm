package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.interfaces.IPlayListService;

public class DeletePlaylistCommand implements ICommand {
    private final IPlayListService playListService;

    public DeletePlaylistCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userID = tokens.get(1);
        String playlistID = tokens.get(2);

        try {
            playListService.delete(userID, playlistID);
            System.out.println("Delete Successful");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}