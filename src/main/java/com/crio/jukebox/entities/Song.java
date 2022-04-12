package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {
    private final String title;
    private final String album;
    private final String mainArtist;
    private final List<String> artistList;

    public Song(String id, String title, String album, String mainArtist, List<String> artistList) {
        this(title, album, mainArtist, artistList);
        this.id = id;
    }

    public Song(String title, String album, String mainArtist, List<String> artistList) {
        this.title = title;
        this.album = album;
        this.mainArtist = mainArtist;
        this.artistList = artistList;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getMainArtist() {
        return mainArtist;
    }

    public List<String> getArtistList() {
        return artistList;
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
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", album=" + album + ", title=" + title + ", mainArtist=" + mainArtist + ", artistList=" + artistList + "]";
    }
    
}