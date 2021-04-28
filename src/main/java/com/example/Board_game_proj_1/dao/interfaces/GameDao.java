package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;

import java.io.IOException;
import java.util.List;

public interface GameDao {
    /**
     * URL for get data from API
     */
    String URL = "https://api.boardgameatlas.com/api/search?client_id=BE1Mg8GUFu";
    //String URL_WITH_HANDLE = "https://api.boardgameatlas.com/api/search?name=HANDLE_NAME&client_id=BE1Mg8GUFu";
    String URL_WITH_HANDLE = "https://api.boardgameatlas.com/api/search?name=HANDLE_NAME&limit=100&fuzzy_match=true&client_id=admin";


    Game findById(String id);
    GameDto findDTOById(String id);
    List<Game> findByName(String name);
    void save(Game game);
    void update(Game game);
    void delete(Game game);
    void deleteById(String id);
    List<Game> findAll();
    List<Mechanic> getGameMechanicsById(String idGame);
    List<Category> getGameCategoriesById(String idGame);
    boolean uploadFromAPI() throws IOException;
}
