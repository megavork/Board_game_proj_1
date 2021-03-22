package com.example.Board_game_proj_1.dao;

import com.example.Board_game_proj_1.dao.intf.UserDao;
import com.example.Board_game_proj_1.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.Board_game_proj_1.util.HibernateConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImlp implements UserDao {

    /**
     * Return one category from base
     * @param login
     * @return
     */
    @Override
    public User findByLogin(String login) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        User user = session.get(User.class, login);
        session.close();
        return user;
    }

    /**
     * Save Object in category base
     * @param user
     * @return
     */
    public User save(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            return findByLogin(user.getLogin());
        } catch (RuntimeException e) {
            if (tx1 != null) tx1.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Update one category in base
     * @param user
     */
    public boolean update(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx1 != null) tx1.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Delete one category from base
     * @param user
     */
    public boolean delete(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx1 != null) tx1.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Return all objects from base
     * @return
     */
    public List<User> findAllUsers() {
        try {
            List<User> user = (List<User>) HibernateConfig.getSessionFactory().openSession().createQuery("From User").list();
            return user;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findFirstUser() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<User> user = (List<User>) session.createQuery("From User where user_role = 0").list();
        session.close();
        return user.get(1);
    }
}
