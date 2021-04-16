package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;

import java.util.List;

public interface CategoryService {

    Category findCategoryById(String idCategory);
    void saveCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    List<Category> findAll();
    boolean uploadFromAPI();
    boolean setGameListForEachCategory();
    List<Category> getCountOfGameFromEachCategory (String count);
}
