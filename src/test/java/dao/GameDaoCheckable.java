package dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import services.GameService;


public class GameDaoCheckable {

    @Test
    @Ignore
    public void uploadCheckable() {
        GameService gameService = new GameService();
        Assert.assertTrue(gameService.uploadFromAPI());
    }
    @Test
    public void getGameMechanicsById() {
        GameService gameService = new GameService();
        Assert.assertNotNull(gameService.getGameMechanicsById("8xos44jY7Q"));
    }
    @Test
    public void getGameCategoriesById() {
        GameService gameService = new GameService();
        Assert.assertNotNull(gameService.getGameCategoriesById("8xos44jY7Q"));
    }
}
