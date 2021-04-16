package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.UserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Data
@Table(schema = "board_game_sch", name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @Column(name = "username", length = 45)
    String username;
    @Column(name = "user_role")
    String user_role;
    @Column(name = "password", length = 100)
    String password;
    @Column(name = "email")
    String email;
    @Column(name = "token")
    String token;

    @Transient
    private boolean isAccountNonExpired;
    @Transient
    private boolean isEnabled;
    @Transient
    private boolean isAccountNonLocked;
    @Transient
    private boolean isCredentialsNonExpired;

    public User() {
        this.user_role = "USER";
    }

    public void unlockAccount() {
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isEnabled = true;
        this.isCredentialsNonExpired = true;
    }

    /**
     * Constructor int user_role, String login, String password, String email, String token
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

    public UserDto toUserDto(String login) {
        UserDto userDto = new UserDto();
        userDto.setUsername(login);
        return userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRoleEntity().getName())
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isCredentialsNonExpired;
    }
}
