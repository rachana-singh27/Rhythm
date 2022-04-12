package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommad;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadSongDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.commands.RunPlaylistCommand;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.repositories.interfaces.IPlayListRepository;
import com.crio.jukebox.repositories.interfaces.ISongRepository;
import com.crio.jukebox.repositories.interfaces.IUserRepository;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;
import com.crio.jukebox.services.interfaces.IPlayListService;
import com.crio.jukebox.services.interfaces.ISongService;
import com.crio.jukebox.services.interfaces.IUserService;

public class ApplicationConfig {
    private final IUserRepository userRepository = new UserRepository();
    private final IPlayListRepository playListRepository = new PlayListRepository();
    private final ISongRepository songRepository = new SongRepository();

    private final IUserService userService = new UserService(userRepository);
    private final IPlayListService playListService = new PlayListService(playListRepository, userRepository, songRepository);
    private final ISongService songService = new SongService(songRepository, userRepository, playListRepository);

    private final LoadSongDataCommand loadSongDataCommand = new LoadSongDataCommand(songService);
    private final CreateUserCommad createUserCommand = new CreateUserCommad(userService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playListService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playListService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playListService);
    private final RunPlaylistCommand runPlaylistCommand = new RunPlaylistCommand(playListService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(songService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadSongDataCommand);
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",runPlaylistCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
         
        return commandInvoker;
    }
    
}