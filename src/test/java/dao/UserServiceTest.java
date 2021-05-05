package dao;

import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void saveUSer() {
        Assert.assertFalse(userService.save(new User(0,"Megavork","password","megavork@mail.ru")));
    }
}
