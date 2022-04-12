package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.interfaces.ISongService;

public class PlaySongCommand implements ICommand {
    private final ISongService songService;

    public PlaySongCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userID = tokens.get(1);
        String operation = tokens.get(2);

        try {
            Song song = songService.play(userID, operation);
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