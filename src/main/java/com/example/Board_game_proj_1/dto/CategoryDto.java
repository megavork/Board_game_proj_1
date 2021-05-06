package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.Game;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
        String idCategories;
        String name;

        List<Game> gameList = new ArrayList<>();
}
