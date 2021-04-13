package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImlp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    /**
     * Return one category from base
     * @param username
     * @return
     */
    @Override
    public User findByLogin(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        /*Query query = session.createQuery(  "From User where username IN (:username)");
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();*/
        return user;
    }

    /**
     * Save Object in category base. (By using session.persist())
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.persist(user);
            return findByLogin(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update one category in base.
     * @param user
     */
    @Override
    public boolean update(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete one category from base
     * @param user
     */
    @Override
    public boolean delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return all objects from base
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        try {
            List list = session.createQuery("From User").list();
            return list;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * NEED TO CHECK
     * @return
     */
    @Override
    public User findFirstUser() {
        Session session = sessionFactory.getCurrentSession();
        try {
            User user = (User) session.createQuery("SELECT TOP (1) From User where user_role = 0").list();
            return user;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }

    }
}
