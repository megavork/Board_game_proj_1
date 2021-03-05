package util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;


public class JSONParser {


    public static JSONArray JSONParser(String jsonMechanics, String objectName) throws IOException {
        JSONObject jsonObject = new JSONObject(jsonMechanics);
        JSONArray array =  jsonObject.getJSONArray(objectName);

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
