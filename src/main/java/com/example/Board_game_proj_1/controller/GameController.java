package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.filter.ResponseEntityFilter;
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

        return ResponseEntityFilter.getListHttpCode(gameList);
    }

    /**
     * It was used just to upload data in base from free API. Not using in my API
     * @return
     */
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
        return ResponseEntityFilter.getObjectHttpCode(gameDto);
    }

    @RequestMapping(value = "/getGameById", method = RequestMethod.GET)
    public ResponseEntity getGame(String ID)  {
        Game game = gameService.findById(ID);
        return ResponseEntityFilter.getObjectHttpCode(game);
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
