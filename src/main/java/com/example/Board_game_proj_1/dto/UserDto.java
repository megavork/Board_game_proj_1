package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //password will not transferring
    //role will not transferring
    public User toUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password); //добавить методы валидации!!!!!!!!!!!!
        user.setEmail("");
        user.setUser_role(1);
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
