package util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class JSONParser {


    public static JSONArray JSONParseMechanics(String jsonMechanics) throws IOException {
        JSONObject jsonObject = new JSONObject(jsonMechanics);
        JSONArray array =  jsonObject.getJSONArray("mechanics");

        return array;
    }


    private String[] JSONParser(String jsonString) {
        jsonString = jsonString.substring(jsonString.indexOf("["),jsonString.indexOf("]"));
        String[] result = jsonString.replace("[","").replace("]","").split("},");

        for(int i=0; i<result.length; i++) {
            result[i]+="}";
        }
        return result;
    }
}
