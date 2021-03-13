package services;

import dao.UserDao;
import entity.User;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * Return one User from base
     * @param idUser
     * @return
     */
    public User findUserById(String idUser) {
        return userDao.findById(idUser);
    }

    /**
     * Save Object in User base
     * @param user
     */
    public boolean saveUser(User user) {
        return userDao.save(user);
    }

    /**
     * Update one User in base
     * @param user
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * Delete one User from base
     * @param user
     */
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    /**
     * Return all User from base
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

}
