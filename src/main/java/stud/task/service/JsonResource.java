package stud.task.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonResource {

    private ResLoader loader = ResLoader.getInstance();
    private final String POSTFIX = ".json";

    public JSONArray getArray(JSONObject object, String key) {
        return object.getJSONArray(key);
    }

    public JSONArray arrayFromFile(String name) {
        name = fixPath(name);
        String text = getTextFromFile(name);
        return new JSONArray(text);
    }

    public JSONObject objectFromFile(String name) {
        name = fixPath(name);
        String text = getTextFromFile(name);
        return new JSONObject(text);
    }

    private String getTextFromFile(String name) {
        name = fixPath(name);
        Path path = loader.getPath(name);
        String text = null;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private String fixPath(String path) {
        if (POSTFIX.equals(path.substring(path.lastIndexOf('.'), path.length()-1))) return path;
        else
            return path + POSTFIX;
    }
}
