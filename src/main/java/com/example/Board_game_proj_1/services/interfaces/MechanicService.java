package com.example.Board_game_proj_1.services.interfaces;

import com.example.Board_game_proj_1.entity.Mechanic;

import java.util.List;

public interface MechanicService {

    Mechanic findMechanicById(String idMechanic);
    void saveMechanic(Mechanic category);
    void updateMechanic(Mechanic category);
    void deleteMechanic(Mechanic category);
    List<Mechanic> findAll();
    boolean uploadFromAPI();
}
