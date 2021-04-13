package com.example.Board_game_proj_1.dao;

import com.example.Board_game_proj_1.dao.classes.UserDaoImlp;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.classes.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class UserServiceImplTest {
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void saveUSer() {
        UserDaoImlp userDaoImlp = applicationContext.getBean("sampleBean", UserDaoImlp.class);
        Assert.assertNotNull(userDaoImlp);
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
