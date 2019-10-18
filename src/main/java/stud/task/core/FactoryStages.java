package stud.task.core;

import org.json.JSONObject;
import stud.task.core.stages.Stage;
import stud.task.service.JsonResource;
import stud.task.util.FactoryObjects;

import java.util.LinkedList;
import java.util.List;

public class FactoryStages {

    private FactoryObjects<Stage> fc;

    private final String PATH_TO_DETERS = "stage/stage.json";
    private final List<String> names;

    private final String F_DETERS = "stages";
    private final String F_PREFIX = "prefix";
    private final String F_POSTFIX = "postfix";

    private static FactoryStages ourInstance = new FactoryStages();

    public static FactoryStages getInstance() {
        return ourInstance;
    }

    private FactoryStages() {
        fc = new FactoryObjects<>();
        names = new LinkedList<>();
        JsonResource parser = new JsonResource();
        JSONObject deters = parser.objectFromFile(PATH_TO_DETERS);
        String pref = deters.getString(F_PREFIX);
        String post = deters.getString(F_POSTFIX);
        deters.getJSONArray(F_DETERS).forEach(
                s -> names.add(String.format("%s%s%s", pref, (String) s, post))
        );
    }

    public List<Stage> create() {
        return fc.create(names);
    }
}
