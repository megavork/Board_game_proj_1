package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DtoUserController {

    @Autowired
    private UserService userService;

    //should return token
    @PostMapping(value = "/login")
    public String login(@RequestBody UserDto userDto)  {
        return userService.isAuthorized(userDto);
    }

/*    @GetMapping(value = "/user")
    public User getUserDto(@RequestBody String jsonObject)  {
        JSONObject object = new JSONObject(jsonObject);
        String username = object.optString("username");
        return userService.findByLogin(username);
    }*/

    @GetMapping(value = "/user")
    public UserDto getUserDto(@RequestParam String username) {
        User user = userService.findByLogin(username);
        return user.toUserDto(user.getUsername());
    }
}
