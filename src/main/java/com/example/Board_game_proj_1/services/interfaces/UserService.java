package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;

import java.util.List;

public interface UserService {

    User findByLogin(String login);
    User save(User user);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAllUsers();
    User findFirstUser();
    boolean isAuthorized(UserDto userDto);
}
