package services;

import dao.GameDao;
import entity.Category;
import entity.Game;
import entity.Mechanic;
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
     * Save Object in category base
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
