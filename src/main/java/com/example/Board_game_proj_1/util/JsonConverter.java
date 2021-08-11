package com.example.Board_game_proj_1.util;

import org.json.JSONObject;

public class JsonConverter {
    public static String toJson(String key, String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"").append(key).append("\": \"").append(value).append("\"}");
//        String result = "{\"" + key + "\": \"" + value + "\"}";
        JSONObject object = new JSONObject(stringBuilder);
        return object.toString();
    }
}
