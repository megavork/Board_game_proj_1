package com.example.Board_game_proj_1.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String username;
    private String token;

    public UserDto() {
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String object = null;
        try {
            object = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }
}
