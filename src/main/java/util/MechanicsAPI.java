package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class MechanicsAPI {

    final static String dataBaseURL = "jdbc:mysql://localhost:3306/Board_game_sch";

    /**
     * Will got all mechanics from API
     * https://api.boardgameatlas.com/api/game/mechanics?client_id=BE1Mg8GUFu
     * https://www.boardgameatlas.com/api/docs
     * @return
     * @throws IOException
     */
    public static String getMechanicsFromAPI() throws IOException {
        //mechanics
        String url = "https://api.boardgameatlas.com/api/game/mechanics?client_id=BE1Mg8GUFu";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        //read full JSON from API
        inputLine = in.lines().collect(Collectors.joining());
        in.close();

        return inputLine;
    }
}
