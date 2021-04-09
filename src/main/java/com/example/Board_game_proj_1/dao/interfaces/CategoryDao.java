package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;

import java.util.List;

public interface CategoryDao {
    /**
     * URL for get data from API
     */
    String URL = "https://api.boardgameatlas.com/api/game/categories?client_id=BE1Mg8GUFu";
    String GET_GAME_URL = "https://api.boardgameatlas.com/api/search?categories=CATEGORY_ID&client_id=BE1Mg8GUFu";
    String OBJECT_NAME = "categories";

    Category findById(String id);
    void save(Category category);
    void update(Category category);
    void delete(Category category);
    List findAll();
    boolean uploadFromAPI();
    boolean setGameListForEachCategory();
    public List<Game> getGames (String id);
}
