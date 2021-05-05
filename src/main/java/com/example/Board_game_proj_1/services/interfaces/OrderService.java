package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.entity.Game;

import java.util.List;

public interface OrderService {

    boolean create(String username, List<String> gameIds);
    boolean delete(String username, String orderId);
    List<Game> getOrders(String username);
}
