package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.GameDao;
import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class GameServiceImpl implements GameService{

    @Autowired
    GameDao gameDao;

    /**
     * Return one Game from base
     * @param idGame
     * @return
     */
    @Override
    @Transactional
    public Game findById(String idGame) {
        return gameDao.findById(idGame);
    }

    /**
     * Save Object in Game base
     * @param game
     */
    @Override
    @Transactional
    public void save(Game game) {
        gameDao.save(game);
    }

    /**
     * Update one Game in base
     * @param game
     */
    @Override
    @Transactional
    public void update(Game game) {
        gameDao.update(game);
    }

    /**
     * Delete one Game
     * @param game
     */
    @Override
    @Transactional
    public void delete(Game game) {
        gameDao.delete(game);
    }

    /**
     * Delete one Game from base
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(String id) {
        gameDao.deleteById(id);
    }

    /**
     * Return all Games from base
     * @return
     */
    @Override
    @Transactional
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    /**
     * Method will return all mechanics which depends on idGame
     * @param idGame
     * @return
     */
    @Override
    @Transactional
    public List<Mechanic> getGameMechanicsById(String idGame) {
        return gameDao.getGameMechanicsById(idGame);
    }

    @Override
    @Transactional
    public List<Mechanic> getGameMechanic(String idGame) {
        return gameDao.findById(idGame).getMechanicsTable();
    }

    @Override
    @Transactional
    public List<Category> getGameCategory(String idGame) {
        return gameDao.findById(idGame).getCategoryTable();
    }

    /**
     * Method will return all categories which depends on idGame
     * @param idGame
     * @return
     */
    @Override
    @Transactional
    public List<Category> getGameCategoriesById(String idGame) {
        return gameDao.findById(idGame).getCategoryTable();
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    @Override
    @Transactional
    public boolean uploadAllGamesFromAPI() throws IOException {
        return gameDao.uploadAllGamesFromAPI();
    }

    @Override
    @Transactional
    public List<GameDto> convertToGameDtoList(List<Game> list) {
        return list.stream().map(Game::fromGameToGameDto).collect(Collectors.toList());
    }

}
