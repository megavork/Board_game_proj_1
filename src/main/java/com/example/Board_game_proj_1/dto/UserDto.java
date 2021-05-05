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

/*    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String object = null;
        try {
            object = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }*/
}
