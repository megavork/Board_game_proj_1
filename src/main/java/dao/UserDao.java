package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateConfig;

import java.util.List;

public class UserDao {

    /**
     * Return one category from base
     * @param id
     * @return
     */
    public static User findById(String id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    /**
     * Save Object in category base
     * @param user
     * @return
     */
    public boolean save(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(user);
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
     * Update one category in base
     * @param user
     */
    public void update(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    /**
     * Delete one category from base
     * @param user
     */
    public void delete(User user) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    /**
     * Return all objects from base
     * @return
     */
    public List<User> findAll() {
        List<User> user = (List<User>) HibernateConfig.getSessionFactory().openSession().createQuery("From User").list();
        return user;
    }
}
