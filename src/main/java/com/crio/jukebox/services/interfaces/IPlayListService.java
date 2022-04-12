package com.crio.jukebox.services.interfaces;

import java.util.List;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlayListService {
    public Playlist create(String userID, String playlistName, List<String> songIDs) throws Exception;
    public void delete(String userID, String playlistID) throws Exception;
    public Playlist modify(String operation, String userID, String playlistID, List<String> songIDs) throws Exception;
    public Song play(String userID, String playlistID) throws Exception;
    
}