package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.interfaces.IUserService;

public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
    
}