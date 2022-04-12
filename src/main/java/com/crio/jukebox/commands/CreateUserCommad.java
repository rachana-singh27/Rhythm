package com.crio.jukebox.commands;

import java.util.List;

import com.crio.jukebox.services.interfaces.IUserService;
import com.crio.jukebox.entities.User;

public class CreateUserCommad implements ICommand {
    private final IUserService userService;

    public CreateUserCommad(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String name = tokens.get(1);
        User user = userService.create(name);
        System.out.println(user.getId() + " " + user.getName());
    }
}