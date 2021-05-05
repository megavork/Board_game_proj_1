package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import com.example.Board_game_proj_1.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody UserDto userDto) {
        String result = userService.isAuthorized(userDto);

        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(JsonConverter.toJson("token",result));
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity logout(@RequestHeader(name = "Authorization") String token) {
        String userToken = token.substring(token.indexOf(" ")+1);

        if(userService.disableUser(userToken)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(false);
        }
    }

    @GetMapping(value = "/user")
    public UserDto getUserDto(@RequestBody UserDto userDto) {
        User user = userService.findByLogin(userDto.getUsername());
        return user.toUserDto();
    }

    @PostMapping(value = "/register")
    public ResponseEntity saveUser(@RequestBody UserDto userDto) {
        return userService.save(userDto.toUser());
    }

    @GetMapping(value = "/users")
    public ResponseEntity putOrder(@RequestHeader(name = "Authorization") String token) {
        String userToken = token.substring(token.indexOf(" ")+1);
        User user = (User) userService.findByToken(userToken).get();

        if(user.isEnabled()) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }
    /*
    edit user
POST /user/:id
with authorization header
{ username: 'new username', email: 'new email', 'role': 'new role' }
DO NOT OVERRIDE PASSWORD :)
     */
    @PostMapping(value = "/user/edit")
    public ResponseEntity login(@RequestHeader(name = "Authorization") String token, @RequestBody UserDto userDto) {
        String userToken = token.substring(token.indexOf(" ")+1);
        User user = (User) userService.findByToken(userToken).get();

        if(user.getToken().equals(userToken)) {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto.toUser()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }
    }
}
