package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /*
    * Нужен запрос на первые N категорий в каждой из которых лежит M игр, с возможностью подгрузки следующих N категорий и их M игр.
    * */
    @GetMapping("/category")    // http://localhost:8080/category/?category_count=5&game_count=5
    public List<CategoryDto> getCategoryList(@RequestParam String category_count, @RequestParam String game_count) {
        return categoryService.getCountOfGameFromEachCategory(category_count,game_count);
    }
    
}
