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
    List<CategoryDto> findByListId(List listId, int game_count);
    void save(Category category);
    void update(Category category);
    void delete(Category category);
    List findAllWithGames(int limit, int offset);
    List findAll();
    boolean uploadFromAPI();
    List<CategoryDto> getCountOfGameFromEachCategory (int category_count, int game_count, int page_number);

}
