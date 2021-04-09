package com.example.Board_game_proj_1.controller;

import com.example.Board_game_proj_1.dto.GameDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;    //это должен быть интерфейс

    @Autowired
    private GameService gameService;    //это должен быть интерфейс

    @Autowired
    private CategoryService categoryService;   //это должен быть интерфейс

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<User> listUser = userService.findAllUsers();
        List<GameDto> dtoList = gameService.convertToGameDtoList(gameService.findAll());
        List<Category> categoryList = categoryService.findAll();
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView registration() {
        List<User> listUser = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reg_page");
        modelAndView.addObject("listUser", listUser);
        return modelAndView;
    }
}
