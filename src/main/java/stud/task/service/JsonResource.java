package stud.task.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonResource {

    private ResLoader loader = ResLoader.getInstance();

    public JSONArray arrayFromFile(String name) {
        String text = getTextFromFile(name);
        return new JSONArray(text);
    }

    public JSONObject objectFromFile(String name) {
        String text = getTextFromFile(name);
        return new JSONObject(text);
    }

    private String getTextFromFile(String name) {
        Path path = loader.getPath(name);
        String text = null;
        try {
            text = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
