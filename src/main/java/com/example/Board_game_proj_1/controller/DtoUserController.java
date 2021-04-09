package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DtoUserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public @ResponseBody String getUserDto(@RequestBody UserDto userDto)  {
        return userDto.toJson();
    }

}
