package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DtoUserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String getUserDto()  {
        return "Карина, смари, эта херня еще и работает...";
                //userService.isAuthorized(userDto);
    }

    @GetMapping(value = "/user")
    public User getUserDto(@RequestBody String jsonObject)  {
        JSONObject object = new JSONObject(jsonObject);
        String username = object.optString("username");
        System.out.println();
        return userService.findByLogin(username);
    }

}
