package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;

import java.util.List;

public interface CategoryService {

    Category findCategoryById(String idCategory);
    List<CategoryDto> findByListId(List listId, int game_count);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    List<Category> findAllWithGames();
    List<Category> findAll();
    boolean uploadFromAPI();
    List<CategoryDto> getCountOfGameFromEachCategory (int category_count, int game_count, int page_number);
}
