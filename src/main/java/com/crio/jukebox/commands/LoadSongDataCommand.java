package com.crio.jukebox.commands;

import java.io.File;
import java.util.List;

import com.crio.jukebox.services.interfaces.ISongService;

public class LoadSongDataCommand implements ICommand {
    private final ISongService songService;

    public LoadSongDataCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String fileName = tokens.get(1);
        File inputFile = new File(fileName);

        try {
            songService.loadData(inputFile);
            System.out.println("Songs Loaded successfully");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
}