package com.example.Board_game_proj_1.util;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class UploadObjectsFromAPI{

    /**
     * Will got all mechanics from API
     * https://www.boardgameatlas.com/api/docs
     * @return
     * @throws IOException
     */
    public static JSONArray getDateFromAPI(String URL, String objectName) throws IOException {

        URL obj = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        //read full JSON from API
        String inputLine;
        inputLine = in.lines().collect(Collectors.joining());
        in.close();

        JSONArray jsonArray = JSONParser.JSONParser(inputLine,objectName);

        return jsonArray;
    }
}
