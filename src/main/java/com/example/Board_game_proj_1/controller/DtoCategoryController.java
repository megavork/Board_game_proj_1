package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DtoCategoryController {

    @Autowired
    private CategoryService categoryService;

}
