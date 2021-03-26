package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@Controller
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

    @GetMapping("/categoryList")
    public List<Category> getCategoryList() {
        return categoryService.findAll();
    }
}
