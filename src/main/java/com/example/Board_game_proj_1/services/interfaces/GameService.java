package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.dao.interfaces.GameDao;
import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface GameService {
    
    Game findById(String id);
    GameDto findDTOById(String id);
    List<Game> findByListId(List<String> listId);
    void save(Game game);
    void update(Game game);
    void delete(Game game);
    List<Game> findAll();
    boolean uploadFromAPI() throws IOException;
    List<Mechanic> getGameMechanic(String idGame);
    List<Category> getGameCategory(String idGame);
}
