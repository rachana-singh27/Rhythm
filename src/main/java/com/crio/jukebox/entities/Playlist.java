package com.crio.jukebox.entities;

import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends BaseEntity {
    private final String name;
    private final List<Song> songs;
    private final User creator;
    private String currentSongID = "1";

    public Playlist(String id, String name, List<Song> songs, User creator) {
        this(name,songs,creator);
        this.id = id;
    }

    public Playlist(String name, List<Song> songs, User creator) {
        this.name = name;
        this.songs = songs;
        this.creator = creator;
    }
    
    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs.stream().collect(Collectors.toList());
    }

    public User getCreator() {
        return creator;
    }

    public String getCurrentSongID() {
        return currentSongID;
    }

    public void setCurrentSongID(String songID) {
        this.currentSongID = songID;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Playlist [id=" + id + ", name=" + name +  ", creator=" + creator.getName() + ", songs=" + songs + "]";
    }
  
}