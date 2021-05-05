package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories/games")       //TIME
    public ResponseEntity getCategoriesGames(@RequestBody Map<String,String> object) {
        int category_count = Integer.parseInt(object.get("category_count"));
        int game_count = Integer.parseInt(object.get("game_count"));
        int page_number = Integer.parseInt(object.get("page_number"));
        List<CategoryDto>  categoryDtoList = categoryService.getCountOfGameFromEachCategory(category_count,game_count, page_number);

        if(categoryDtoList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList());
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoryDtoList);
    }

    @GetMapping("/categories/list")
    public ResponseEntity getCategoriesList() {
        List<Category> categoryList = categoryService.findAll();
        if(categoryList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList());
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoryList);
    }

    @PostMapping("/categories/search/list")
    public ResponseEntity getCategoriesSearchByList(@RequestBody Map<String, List> object) {
        List objectList = object.get("categories");
        List<Category> categoryList = categoryService.findByListId(objectList,5);

        if(categoryList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList());
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(categoryList);
    }


    
}
