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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private HttpStatus status;

    @PostMapping("/games")
    public ResponseEntity<List<CategoryDto>> getCategoriesGames(@RequestBody Map<String,String> object) {
        int category_count = Integer.parseInt(object.get("category_count"));
        int game_count = Integer.parseInt(object.get("game_count"));
        int page_number = Integer.parseInt(object.get("page_number"));

        List<CategoryDto>  categoryDtoList = categoryService.getCountOfGameFromEachCategory(category_count,game_count, page_number);
        status = HttpStatus.OK;
        if(categoryDtoList.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(categoryDtoList, status);
    }

    @GetMapping("/list")
    public ResponseEntity getCategoriesList() {
        List<Category> categoryList = categoryService.findAll();
        status = HttpStatus.OK;
        if(categoryList.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(categoryList, status);
    }

    @PostMapping("/search/list")
    public ResponseEntity getCategoriesSearchByList(@RequestBody Map<String, List<Category>> object) {
        List objectList = object.get("categories");
        List<Category> categoryList = categoryService.findByListId(objectList,5);
        status = HttpStatus.OK;

        if(categoryList.isEmpty()) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(categoryList, status);
    }


    
}
