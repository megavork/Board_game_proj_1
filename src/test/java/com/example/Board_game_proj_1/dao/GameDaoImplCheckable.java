package com.example.Board_game_proj_1.dao;

import com.example.Board_game_proj_1.dao.classes.GameDaoImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import com.example.Board_game_proj_1.services.classes.GameServiceImpl;

import java.io.IOException;

public class GameDaoImplCheckable {

    @Test
    @Ignore
    public void uploadCheckable() throws IOException {
        GameDaoImpl gameDao = new GameDaoImpl();
        Assert.assertTrue(gameDao.uploadAllGamesFromAPI());
    }
    @Test
    public void getGameMechanicsById() {
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        Assert.assertNotNull(gameServiceImpl.getGameMechanicsById("8xos44jY7Q"));
    }
    @Test
    public void getGameCategoriesById() {
        GameServiceImpl gameServiceImpl = new GameServiceImpl();
        Assert.assertNotNull(gameServiceImpl.getGameCategoriesById("8xos44jY7Q"));
    }
}
