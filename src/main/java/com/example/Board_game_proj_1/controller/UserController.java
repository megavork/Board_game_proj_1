package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private UserService userService;    //это должен быть интерфейс

    @Autowired
    private GameService gameService;    //это должен быть интерфейс

    @Autowired
    private CategoryService categoryService;   //это должен быть интерфейс

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<User> listUser = userService.findAllUsers();
        //List<GameDto> dtoList = gameService.convertToGameDtoList(gameService.findAll());
        //List<Category> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("listUser", listUser);
        //modelAndView.addObject("gameList", dtoList);
        //modelAndView.addObject("categoryList", categoryList);
        return modelAndView;
    }

    @GetMapping("/list")
    public List getUserList() {
        List<User> listUser = userService.findAllUsers();
        return listUser;
    }

    //сделай чтобы у дто и юзера совпадали поля, но у дто некоторые могли быть = 0...это для логинки...и не ноль - при регистрации.
    @RequestMapping(value = "/registr", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
