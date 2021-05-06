package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "board_game_sch", name = "orders")
public class Order implements Serializable {

    @Id
    @Column(name = "orderID")
    private int orderID;

    @Column(name = "userID")
    private int userID;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name="depend_game_order",
            joinColumns=@JoinColumn (name="orderID"),
            inverseJoinColumns=@JoinColumn(name="gameID"))
    List<Game> games = new ArrayList<>();

    public Order() {
        this.orderID = (int)System.currentTimeMillis();
        System.out.println(this.orderID);
    }

    public OrderDto toOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(this.getOrderID());
        orderDto.setUserID(this.getUserID());
        orderDto.setGames(this.getGames());
        return orderDto;
    }
}
