package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * URL for get data from API
     */
    //String GET_GAME_URL = "https://api.boardgameatlas.com/api/search?categories=CATEGORY_ID&client_id=admin";

    Category findById(String id);
    void save(Category category);
    void update(Category category);
    void delete(Category category);
    List findAll();
    boolean uploadFromAPI();
    List<Category> findFirstCount(int count);
    List<CategoryDto> getCountOfGameFromEachCategory (String category_count, String game_count);

}
