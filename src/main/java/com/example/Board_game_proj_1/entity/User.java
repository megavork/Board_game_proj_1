package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Data
@Table(schema = "board_game_sch", name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "login", length = 45)
    String login;
    @Column(name = "user_role")
    int user_role;
    @Column(name = "password", length = 45)
    String password;
    @Column(name = "email")
    String email;

    /**
     * Constructor int user_role, String login, String password, String email
     * @param user_role
     * @param login
     * @param password
     * @param email
     */
    public User(int user_role, String login, String password, String email) {
        this.user_role = user_role;
        this.login = login;
        this.email = email;
        this.password = String.valueOf(password.hashCode());
    }

    public UserDto toUserDto(String login, String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(login);
        userDto.setPassword(password);
        return userDto;
    }

}
