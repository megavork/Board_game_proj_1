package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * Return User by using Login
     * @param login
     * @return
     */
    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    /**
     * Save Object in User base
     * @param user
     */
    @Override
    @Transactional
    public User save(User user) {
        userDao.save(user);
        return userDao.findByLogin(user.getLogin());
    }

    /**
     * Update one User in base
     * @param user
     */
    @Override
    @Transactional
    public boolean update(User user) {
        return userDao.update(user);
    }

    /**
     * Delete one User from base
     * @param user
     */
    @Override
    @Transactional
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    /**
     * Return all User from base
     * @return
     */
    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    /**
     * Return only one first User from table
     * @return
     */
    @Override
    @Transactional
    public User findFirstUser() {
        return userDao.findFirstUser();
    }

}
