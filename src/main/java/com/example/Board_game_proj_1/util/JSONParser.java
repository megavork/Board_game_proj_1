package com.example.Board_game_proj_1.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {

    public static JSONArray JSONParser(String jsonLine, String objectName) {

        JSONObject jsonObject = new JSONObject(jsonLine);
        JSONArray array =  jsonObject.getJSONArray(objectName);
        return array;
    }
}
