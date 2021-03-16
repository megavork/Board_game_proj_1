package com.example.Board_game_proj_1.dao.intf;

import com.example.Board_game_proj_1.entity.User;

import java.util.List;

public interface UserDao {

    User findById(int id);
    boolean save(User user);
    boolean update(User user);
    boolean delete(User user);
    List<User> findAllUsers();
    User findFirstUser();
}
