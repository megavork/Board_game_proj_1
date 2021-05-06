package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImlp implements UserDao {

    private final int TOKEN_LENGTH = 36;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findById(int ID) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, ID);
        return user;
    }

    /**
     * Return one category from base
     * @param username
     * @return
     */
    @Override
    public User findByLogin(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(  "From User where username = :username");
        query.setParameter("username", username);
        User user;
        try {
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(  "From User where email = :email");
        query.setParameter("email", email);
        User user;
        try {
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    @Override
    public User findByToken(String token) {
        Session session = sessionFactory.getCurrentSession();

        Query query =  session.createQuery("From User where token = :token");
        query.setParameter("token", token);
        try {
            User user = (User) query.getSingleResult();
            if(user == null) {
                return null;
            } else {
                user.enable();
                return user;
            }
        } catch (NoResultException nre) {
            System.out.println("No User found for query");
            return new User().disable();
        }
    }

    @Override
    public boolean disableUser(String token) {
        if(token.length() < TOKEN_LENGTH) {
            return false;
        } else {
            User user = findByToken(token);
            if(!user.getToken().isEmpty()) {
                save(user.disable());
            }

            return true;
        }
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
            user.enable();
            session.persist(user);
            return findByLogin(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        }
    }

    /**
     * Update one category in base.
     * @param user
     */
    @Override
    public boolean update(User user) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Session session = sessionFactory.getCurrentSession();
/*        User newUser = findByToken(user.getToken());//!!!!!!!!!!!!!!!!!!!!!!!!!!!
        newUser.setUsername(user.getUsername());
        newUser.setUser_role(user.getUser_role());
        newUser.setEmail(user.getEmail());*/

        try {
//            session.saveOrUpdate(newUser);
            session.saveOrUpdate(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateByID(int id, UserDto userDto) {
        Session session = sessionFactory.getCurrentSession();
        User user = findById(id);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setUser_role(userDto.getUser_role());
        session.saveOrUpdate(user);
        return true;
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
    public List<UserDto> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<UserDto> dtoList = new ArrayList<>();
        try {
            List<User> list = session.createQuery("From User").getResultList();
            for(User user: list) {
                dtoList.add(user.toUserDto());
            }
            return dtoList;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return dtoList;
        }
    }
}
