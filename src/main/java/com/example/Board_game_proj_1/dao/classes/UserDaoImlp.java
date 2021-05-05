package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.OrderDao;
import com.example.Board_game_proj_1.dao.interfaces.UserDao;
import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImlp implements UserDao, OrderDao {

    private final int TOKEN_LENGTH = 36;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GameService gameService;

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
        Session session = sessionFactory.getCurrentSession();
        User newUser = findByToken(user.getToken());
        newUser.setUsername(user.getUsername());
        newUser.setUser_role(user.getUser_role());
        newUser.setEmail(user.getEmail());

        try {
            session.saveOrUpdate(newUser);
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

    @Override
    public boolean create(String username, List<String> gameIds) {
        List<Game> gameList = gameService.findByListId(gameIds);
        User user = findByLogin(username);
        if(!gameList.isEmpty() && user != null) {
            user.setOrderGameList(gameList);
            save(user);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Delete order by using username and order ID
     * @param username
     * @param orderId
     * @return boolean
     */
    @Override
    public boolean delete(String username, String orderId) {
        User user = findByLogin(username);
        //user.getOrderGameList().stream().peek()
        return true;
    }

    @Override
    public List<Game> getOrders(String username) {
        List<Game> gameList = findByLogin(username).getOrderGameList();
        if(!gameList.isEmpty())
            return findByLogin(username).getOrderGameList();
        else
            return new ArrayList<>();
    }
}
