package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MechanicController {
    @Autowired
    MechanicService mechanicService;

    @Autowired
    private GameService gameService;

    @GetMapping("/mechanicUpload")
    public List<Mechanic> uploadMechanicFromAPI() {
        if(mechanicService.uploadFromAPI()) {
            return mechanicService.findAll();
        } else  {
            return null;
        }
    }

    @GetMapping("/mechanicList")
    public List<Mechanic> getMechanicList() {
        return mechanicService.findAll();
    }

}
