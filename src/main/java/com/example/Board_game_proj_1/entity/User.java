package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.UserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Data
@Table(schema = "board_game_sch", name = "users")
public class User implements Serializable, UserDetails {

    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @Column(name = "userId")
    private int userId;
    @Column(name = "username", length = 45)
    private String username;
    @Column(name = "user_role")
    private String user_role;
    @Column(name = "password", length = 100)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "token")
    private String token;

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
        this.token = "";
        this.enable();
    }

    public void enable() {
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isEnabled = true;
        this.isCredentialsNonExpired = true;
    }

    public User disable() {
        this.isAccountNonExpired = false;
        this.isAccountNonLocked = false;
        this.isEnabled = false;
        this.isCredentialsNonExpired = false;
        this.setToken(null);
        return this;
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

    public UserDto toUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername(this.getUsername());
        userDto.setEmail(this.getEmail());
        userDto.setUserID(this.getUserId());
        userDto.setUser_role(this.getUser_role());
        return userDto;
    }

    public void setToken() {
        this.setToken(UUID.randomUUID().toString());
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
