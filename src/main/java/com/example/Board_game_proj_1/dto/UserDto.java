package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.User;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String username;
    private String email;
    private String user_role;
    private String password;
    private int userID;

    public UserDto() {
        this.password = "";
        this.user_role = "USER";
    }

    public User toUser() {
        User user = new User();
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setEmail(this.getEmail());
        return user;
    }
}
