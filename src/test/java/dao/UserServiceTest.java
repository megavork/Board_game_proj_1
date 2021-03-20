package dao;

import entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import services.UserService;

public class UserServiceTest {
    UserService userService = new UserService();

    @Test
    public void saveUSer() {
        Assert.assertFalse(userService.saveUser(new User(0,"Megavork","password","megavork@mail.ru")));
    }
}
