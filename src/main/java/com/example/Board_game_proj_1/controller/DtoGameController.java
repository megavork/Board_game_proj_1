package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DtoGameController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/getById/id={idGame}")
    public Game getDtoGame(@PathVariable("idGame") String id)  {
        return gameService.findById(id);
    }

    @GetMapping(value = "/gameList")
    public List<GameDto> getFullGameList()  {
        return gameService.convertToGameDtoList(gameService.findAll());
    }

    @GetMapping(value = "/gameCategory/id={idGame}")
    public List<Category> getCategoryGame(@PathVariable("idGame") String id)  {
        return gameService.getGameCategory(id);
    }

    @GetMapping(value = "/gameMechanic/id={idGame}")
    public List<Mechanic> getMechanicGame(@PathVariable("idGame") String id)  {
        return gameService.getGameMechanic(id);
    }


}
