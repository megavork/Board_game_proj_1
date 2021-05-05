package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.OrderService;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/order/create")
    public ResponseEntity putOrder(@RequestHeader(name = "Authorization") String token, @RequestBody Map<String, List> object) {
        String userToken = token.substring(token.indexOf(" ")+1);
        User user = (User) userService.findByToken(userToken).get();

        if(user.isEnabled()) {
            List<String> gameIds = object.get("gameIds");
            return ResponseEntity.status(HttpStatus.OK).body(userService.create(user.getUsername(), gameIds));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }

    @GetMapping(value = "/orders")
    public ResponseEntity putOrder(@RequestHeader(name = "Authorization") String token) {
        String userToken = token.substring(token.indexOf(" ")+1);
        User user = (User) userService.findByToken(userToken).get();

        if(user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getOrders(user.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }
}
