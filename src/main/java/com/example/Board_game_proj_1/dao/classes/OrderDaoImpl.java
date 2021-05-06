package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.OrderDao;
import com.example.Board_game_proj_1.dto.OrderDto;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Order;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Override
    public List<Order> findByUserId(int userID) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(  "From Order where userID = :userID");
        query.setParameter("userID", userID);
        try {
            List<Order> orders = query.getResultList();
            return orders;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public boolean save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
        return true;
    }

    @Override
    public boolean create(String username, List<String> gameIds) {
        List<Game> gameList = gameService.findByListId(gameIds);
        User user = userService.findByLogin(username);
        if(!gameList.isEmpty() && user != null) {
            Order order = new Order();
            order.setUserID(user.getUserId());
            order.setGames(gameList);
            return save(order);
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
        User user = userService.findByLogin(username);
        //user.getOrderGameList().stream().peek()
        return true;
    }

    @Override
    public List<OrderDto> getOrders(String username) {
        User user = userService.findByLogin(username);
        if(user == null)
            return new ArrayList<>();

        List<Order> orderList = findByUserId(user.getUserId());
        List<OrderDto> dtoOrderList = new ArrayList<>();

        for(Order order: orderList) {
            if(!order.getGames().isEmpty())
                dtoOrderList.add(order.toOrderDto());
        }
        return dtoOrderList;
    }
}
