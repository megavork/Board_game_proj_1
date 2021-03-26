package com.example.Board_game_proj_1.util;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;

public class MappingUtils {
    //из entity в dto
    public UserDto mapToUserDto(User entity){
        UserDto dto = new UserDto();
        dto.setLogin(entity.getLogin());
        dto.setEmail(entity.getEmail());
        return dto;
    }
    //из dto в entity
    public User mapToUserEntity(UserDto dto){
        User entity = new User();
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
