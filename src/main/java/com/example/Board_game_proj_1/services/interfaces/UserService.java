package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findById(int ID);
    User findByLogin(String login);
    ResponseEntity save(User user);
    boolean update(User user);
    boolean updateByID(int id, UserDto user);
    boolean delete(User user);
    List<UserDto> findAllUsers();
    String isAuthorized(UserDto userDto);
    boolean disableUser(String token);
    Optional findByToken(String token);
}
