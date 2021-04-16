package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private UserService customerService;

    @PostMapping("/token")
    public String getToken(@RequestBody UserDto userDto){
        String token= customerService.isAuthorized(userDto);
        if(StringUtils.isEmpty(token)){
            return "no token found";
        }
        return token;
    }
}
