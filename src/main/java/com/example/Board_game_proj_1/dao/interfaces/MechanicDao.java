package com.example.Board_game_proj_1.dao.interfaces;

import com.example.Board_game_proj_1.entity.Mechanic;

import java.util.List;

public interface MechanicDao {

    /**
     * URL for get data from API
     */
    String GET_GAME_URL = "https://api.boardgameatlas.com/api/search?categories=MECHANIC_ID&client_id=BE1Mg8GUFu";

    Mechanic findById(String id);
    void save(Mechanic mechanic);
    void update(Mechanic mechanic);
    void delete(Mechanic mechanic);
    List findAll();
    boolean uploadFromAPI();
}
