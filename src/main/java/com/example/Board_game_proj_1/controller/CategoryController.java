package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import kotlin.ParameterName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categoryUpload")
    public List<Category> uploadCategoryFromAPI() {
        if(categoryService.uploadFromAPI()) {
            return categoryService.findAll();
        } else  {
            return null;
        }
    }

    @GetMapping("/updateGameList")
    public boolean setGameListForEachCategory() {
        return categoryService.setGameListForEachCategory();
    }


    @GetMapping("/category")
    public List<Category> getCategoryList(@RequestParam String count) {
        //нужно вывести по 5 игр от каждой категории
        return categoryService.getCountOfGameFromEachCategory(count);
    }
    
}
