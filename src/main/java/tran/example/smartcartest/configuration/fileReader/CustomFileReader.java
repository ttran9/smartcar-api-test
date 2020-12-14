package tran.example.smartcartest.configuration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class MyFileReader {

    public MyFileReader() { }

    public JSONObject getObjects(String fileName) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getContentFromKey(JSONObject jsonObject, String key) {
        String value = null;
        try {
            value = (String) jsonObject.get(key);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return value;
    }

}
