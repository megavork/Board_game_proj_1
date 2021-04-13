package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto() {
    }

    //password will not transferring
    //role will not transferring
    public User toUser(String login, String password) {
        User user = new User();
        user.setUsername(login);
        user.setPassword(password); //добавить методы валидации!!!!!!!!!!!!
        user.setEmail("");
        user.setUser_role("USER");
        return user;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String object = null;
        try {
            object = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }
}
