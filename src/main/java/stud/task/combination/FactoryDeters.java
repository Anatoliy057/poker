package stud.task.combination;

import org.json.JSONObject;
import stud.task.combination.determinant.CombDeter;
import stud.task.service.JsonResource;

import java.util.LinkedList;
import java.util.List;

public class FactoryDeters {

    private final String PATH_TO_DETERS = "combination/deters.json";
    private final List<String> names;

    private final String F_DETERS = "deters";
    private final String F_PREFIX = "prefix";
    private final String F_POSTFIX = "postfix";

    private static FactoryDeters ourInstance = new FactoryDeters();

    public static FactoryDeters getInstance() {
        return ourInstance;
    }

    private FactoryDeters() {
        names = new LinkedList<>();
        JsonResource parser = new JsonResource();
        JSONObject deters = parser.objectFromFile(PATH_TO_DETERS);
        String pref = deters.getString(F_PREFIX);
        String post = deters.getString(F_POSTFIX);
        deters.getJSONArray(F_DETERS).forEach(
                s -> names.add(String.format("%s%s%s", pref, (String) s, post))
        );
    }

    public List<CombDeter> create() {
        LinkedList<CombDeter> list = new LinkedList<>();
        names.forEach(s -> {
            try {
                list.add((CombDeter) Class.forName(s).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return list;
    }
}
