package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User extends BaseEntity {
    private final String name;
    private final List<Playlist> playlists;
    private String currentPlaylistId;
    
    public User(String id, String name) {
        this(name);
        this.id = id;
    }

    public User(String name) {
        this.name = name;
        this.playlists = new ArrayList<Playlist>();
        this.currentPlaylistId = null;
    } 

    public String getName() {
        return name;
    }

    public void addPlayList(Playlist playlist){
        playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        playlists.removeIf(c -> c.getId() == playlist.getId());
    }

    public List<Playlist> getPlaylists() {
        return playlists.stream().collect(Collectors.toList());
    }

    public boolean checkIfPlayListExists(Playlist playlist){
        for(Playlist c : playlists) {
            if(c.equals(playlist)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlaylistId() {
        return currentPlaylistId;
    }

    public void setCurrentPlaylistId(String currentPlaylistID) {
        this.currentPlaylistId = currentPlaylistID;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", playlists=" + playlists + ", name=" + name + "]";
    }

}