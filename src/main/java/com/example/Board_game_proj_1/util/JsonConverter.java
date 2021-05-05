package com.example.Board_game_proj_1.util;

import org.json.JSONObject;

public class JsonConverter {
    public static String toJson(String key, String value) {
        String result = "{\"" + key + "\": \"" + value + "\"}";
        JSONObject object = new JSONObject(result);
        return object.toString();
    }
}
