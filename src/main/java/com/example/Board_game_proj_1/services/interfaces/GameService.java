package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;

import java.io.IOException;
import java.util.List;

public interface GameService {
    
    Game findById(String id);
    void save(Game game);
    void update(Game game);
    void delete(Game game);
    void deleteById(String id);
    List<Game> findAll();
    List<Mechanic> getGameMechanicsById(String idGame);
    List<Category> getGameCategoriesById(String idGame);
    boolean uploadFromAPI() throws IOException;
    List<Mechanic> getGameMechanic(String idGame);
    List<Category> getGameCategory(String idGame);

    List<GameDto> convertToGameDtoList(List<Game> list);
}
