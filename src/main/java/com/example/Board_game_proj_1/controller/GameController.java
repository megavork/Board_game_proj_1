package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MechanicService mechanicService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public List<Game> uploadGamesFromAPI() {
        try {
            if(gameService.uploadAllGamesFromAPI()) {
                List<Game> gameList = gameService.findAll();
                return gameList;
            } else {
                return null;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/getGameById", method = RequestMethod.GET)
    public Game getGame(String ID)  {
        return gameService.findById(ID);
    }

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
