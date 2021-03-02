package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONParser {
    public static void JSONParse() throws IOException {

        //String url = "https://api.boardgameatlas.com/api/search?order_by=popularity&ascending=false&client_id=BE1Mg8GUFu";
        //String url = "https://api.boardgameatlas.com/api/search?ids=TAAifFP590&client_id=BE1Mg8GUFu";
        //mechanics
        String url = "https://api.boardgameatlas.com/api/game/mechanics?client_id=BE1Mg8GUFu";
        //categories
        //String url = "https://api.boardgameatlas.com/api/game/categories?client_id=BE1Mg8GUFu";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer buffer = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine+"\n");
        }
        in.close();

        System.out.println(buffer.toString());
    }
}
