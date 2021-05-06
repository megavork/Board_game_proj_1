package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Order;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto implements Serializable {

    private int orderId;
    private int userID;
    List<Game> games = new ArrayList<>();

    public OrderDto() {
    }

    public Order toOrder(){
        Order order = new Order();
        order.setOrderID(this.getOrderId());
        order.setUserID(this.getUserID());
        order.setGames(this.getGames());
        return order;
    }
}
