package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public List<Game> uploadGamesFromAPI()  {
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
}
