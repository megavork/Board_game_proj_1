package com.example.Board_game_proj_1.services;

import com.example.Board_game_proj_1.dao.intf.UserDao;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public UserServiceImpl() {
    }

    /**
     * Return one User from base
     * @param idUser
     * @return
     */
    @Override
    public User findById(int idUser) {
        return userDao.findById(idUser);
    }

    /**
     * Save Object in User base
     * @param user
     */
    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    /**
     * Update one User in base
     * @param user
     */
    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    /**
     * Delete one User from base
     * @param user
     */
    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    /**
     * Return all User from base
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    /**
     * Return only one first User from table
     * @return
     */
    @Override
    public User findFirstUser() {
        return userDao.findFirstUser();
    }

}