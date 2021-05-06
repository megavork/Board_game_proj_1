package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import java.util.List;

public interface UserDao {

    User findByLogin(String login);
    User findById(int ID);
    User findByEmail(String email);
    User save(User user);
    boolean update(User user);
    boolean updateByID(int id, UserDto user);
    boolean delete(User user);
    List<UserDto> findAllUsers();
    User findByToken(String token);
    boolean disableUser(String token);
}
