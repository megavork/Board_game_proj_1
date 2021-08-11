package com.example.Board_game_proj_1.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseEntityFilter {

    /**
     * Return responseEntity with HttpStatus
     * @return
     */
    public static ResponseEntity getObjectHttpCode(Object object) {
        HttpStatus status = HttpStatus.OK;

        if(object == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(object, status);
    }

    public static ResponseEntity getListHttpCode(List list) {
        HttpStatus status = HttpStatus.OK;

        if(list == null || list.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(list, status);
    }
}
