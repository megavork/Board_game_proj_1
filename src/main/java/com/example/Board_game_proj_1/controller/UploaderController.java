package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UploaderController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/base/refresh")
    public boolean getCategoryList() throws IOException {
        //return categoryService.uploadFromAPI();
        //return mechanicService.uploadFromAPI();
        return gameService.uploadFromAPI();
    }




}
