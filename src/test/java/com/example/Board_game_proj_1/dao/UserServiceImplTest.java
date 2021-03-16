package com.example.Board_game_proj_1.dao;

import com.example.Board_game_proj_1.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.example.Board_game_proj_1.services.UserServiceImpl;

import java.util.*;

public class UserServiceImplTest {
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Test
    public void saveUSer() {
        Assert.assertTrue(userServiceImpl.save(new User(0,"Sanya","LetsDoIt00-","megavork2@mail.ru")));
    }

    @Test
    public void checkUserPassword() {
        String pass = String.valueOf(("LetsDoIt00-").hashCode());
        String userPass = userServiceImpl.findFirstUser().getPassword();
        Assert.assertTrue(pass.equals(userPass));
    }

    @Test
    public void something() {
        UserServiceImpl userServiceImpl = Mockito.spy(UserServiceImpl.class);
        List<User> list = null;
        Mockito.when(userServiceImpl.findAllUsers()).thenReturn(list);
    }
    @Test
    public void ttt() {


    }

}
