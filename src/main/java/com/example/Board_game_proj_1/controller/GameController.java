package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping(value = "/games/list")
    public ResponseEntity getGamesByList(@RequestBody Map<String, List> object) {
        List objectList = object.get("gameIds");

        List<Game> gameList = gameService.findByListId(objectList);

        if(gameList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(gameList);
        else if(gameList.size() != objectList.size())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(gameList);
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(gameList);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ResponseEntity uploadGamesFromAPI() {
        try {
            if(gameService.uploadFromAPI()) {
                List<Game> gameList = gameService.findAll();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(gameList);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ArrayList<>());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/games/{idGame}")
    public ResponseEntity getDtoGame(@PathVariable("idGame") String id)  {
        GameDto gameDto = gameService.findDTOById(id);
        if(gameDto != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(gameDto);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new GameDto());
        }
    }

    @RequestMapping(value = "/getGameById", method = RequestMethod.GET)
    public ResponseEntity getGame(String ID)  {
        Game game = gameService.findById(ID);
        if(game != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(game);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new Game());
        }
    }
/*
    @GetMapping(value = "/gameCategory/id={idGame}")
    public List<Category> getCategoryGame(@PathVariable("idGame") String id)  {
        return gameService.getGameCategory(id);
    }

    @GetMapping(value = "/gameMechanic/id={idGame}")
    public List<Mechanic> getMechanicGame(@PathVariable("idGame") String id)  {
        return gameService.getGameMechanic(id);
    }*/
}
