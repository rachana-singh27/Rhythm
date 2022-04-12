package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.repositories.interfaces.IPlayListRepository;
 
public class PlayListRepository implements IPlayListRepository {
    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;

    public PlayListRepository() {
        playlistMap = new HashMap<String,Playlist>();
    }

    // public PlayListRepository(Map<String, Playlist> playlistMap) {
    //     this.playlistMap = playlistMap;
    //     this.autoIncrement = playlistMap.size();
    // }

    @Override
    public Playlist save(Playlist entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Playlist p = new Playlist(Integer.toString(autoIncrement),entity.getName(),entity.getSongs(),entity.getCreator());
            playlistMap.put(p.getId(),p);
            return p;
        }
        playlistMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlistMap.values());
    }


    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    @Override
    public Optional<Playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }
    
}