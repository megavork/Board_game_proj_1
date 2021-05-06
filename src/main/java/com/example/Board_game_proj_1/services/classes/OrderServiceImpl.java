package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.OrderDao;
import com.example.Board_game_proj_1.dto.OrderDto;
import com.example.Board_game_proj_1.entity.Order;
import com.example.Board_game_proj_1.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public boolean create(String username, List<String> gameIds) {
        return orderDao.create(username,gameIds);
    }

    @Override
    @Transactional
    public boolean delete(String username, String orderId) {
        return orderDao.delete(username, orderId);
    }

    @Override
    @Transactional
    public List<OrderDto> getOrders(String username) {
        return orderDao.getOrders(username);
    }
}
