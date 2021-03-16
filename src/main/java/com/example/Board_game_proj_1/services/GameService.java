package com.example.Board_game_proj_1.services;

import com.example.Board_game_proj_1.dao.GameDao;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GameService {

    private GameDao gameDao = new GameDao();

    /**
     * Return one Game from base
     * @param idGame
     * @return
     */
    public Game findGameById(String idGame) {
        return gameDao.findById(idGame);
    }

    /**
     * Save Object in Game base
     * @param game
     */
    public void saveGame(Game game) {
        gameDao.save(game);
    }

    /**
     * Update one Game in base
     * @param game
     */
    public void updateGame(Game game) {
        gameDao.update(game);
    }

    /**
     * Delete one Game from base
     * @param game
     */
    public void deleteGame(Game game) {
        gameDao.delete(game);
    }

    /**
     * Return all Games from base
     * @return
     */
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    /**
     * Method will return all mechanics which depends on idGame
     * @param idGame
     * @return
     */
    public List<Mechanic> getGameMechanicsById(String idGame) {
        return gameDao.getGameMechanicsById(idGame);
    }

    /**
     * Method will return all categories which depends on idGame
     * @param idGame
     * @return
     */
    public List<Category> getGameCategoriesById(String idGame) {
        return gameDao.getGameCategoriesById(idGame);
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    public boolean uploadFromAPI() {
        return gameDao.uploadFromAPI();
    }

}
