package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;    //это должен быть интерфейс

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<User> listUser = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("listUser", listUser);
        return modelAndView;
    }

}
