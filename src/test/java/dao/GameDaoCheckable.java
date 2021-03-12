package dao;

import entity.Category;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.List;


public class GameDaoCheckable {

    @Test
    @Ignore
    public void uploadCheckable() {
        GameDao gameDao = new GameDao();
        Assert.assertTrue(gameDao.uploadFromAPI());
    }
    @Test
    public void getGameMechanicsById() {
        GameDao gameDao = new GameDao();
        Assert.assertNotNull(gameDao.getGameMechanicsById("8xos44jY7Q"));
    }
    @Test
    public void getGameCategoriesById() {
        GameDao gameDao = new GameDao();
        Assert.assertNotNull(gameDao.getGameCategoriesById("8xos44jY7Q"));
    }
}
