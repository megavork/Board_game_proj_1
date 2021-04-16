package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByLogin(String login);
    User save(User user);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAllUsers();
    String isAuthorized(UserDto userDto);
    Optional findByToken(String token);
}
