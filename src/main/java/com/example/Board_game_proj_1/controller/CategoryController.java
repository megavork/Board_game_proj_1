package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories/games")       //TIME
    public List<CategoryDto> getCategoriesGames(@RequestBody Map<String,String> object) {
        int category_count = Integer.parseInt(object.get("category_count"));
        int game_count = Integer.parseInt(object.get("game_count"));
        int page_number = Integer.parseInt(object.get("page_number"));
        return categoryService.getCountOfGameFromEachCategory(category_count,game_count, page_number);
    }

    @GetMapping("/categories/list")
    public List<Category> getCategoriesList() {
        List<Category> categoryList = categoryService.findAll();
        return categoryList;
    }

    @PostMapping("/categories/search/list")
    //List<CategoryDto>
    public List<Category> getCategoriesSearchByList(@RequestBody Map<String, List> object) {
        List objectList = object.get("categories");
        System.out.println(objectList.get(0).toString());
        return categoryService.findByListId(objectList,5);
    }


    
}
