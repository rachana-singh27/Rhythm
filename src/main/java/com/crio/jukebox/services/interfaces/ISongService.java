package com.crio.jukebox.services.interfaces;

import java.io.File;

import com.crio.jukebox.entities.Song;

public interface ISongService {
    public void loadData(File inputFile);
    public Song play(String userID, String operation) throws Exception;
    
}