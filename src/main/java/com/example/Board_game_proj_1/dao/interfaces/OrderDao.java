package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.dto.OrderDto;
import com.example.Board_game_proj_1.entity.Order;

import java.util.List;

public interface OrderDao {

    List<Order> findByUserId(int userID);
    boolean save(Order order);
    boolean create(String username, List<String> gameIds);
    boolean delete(String username, String orderId);
    List<OrderDto> getOrders(String username);
}
