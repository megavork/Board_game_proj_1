package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.UserDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Data
@Table(schema = "board_game_sch", name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "username", length = 45)
    String username;
    @Column(name = "user_role")
    String user_role;
    @Column(name = "password", length = 100)
    String password;
    @Column(name = "email")
    String email;

    public User() {
        this.user_role = "USER";
    }

    public User(User user) {
        this.username = user.username;
        this.user_role = user.user_role;
        this.email = user.email;
        this.password = user.password;
        this.email=user.email;
    }

    /**
     * Constructor int user_role, String login, String password, String email
     * @param user_role
     * @param username
     * @param password
     * @param email
     */
    public User(String user_role, String username, String password, String email) {
        this.user_role = user_role;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDto toUserDto(String login, String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(login);
        userDto.setPassword(password);
        return userDto;
    }

}
