package stud.task.combination;

import org.json.JSONObject;
import stud.task.combination.domain.TypeCombination;
import stud.task.service.JsonResource;

import java.util.HashMap;
import java.util.Iterator;

class NamesOfCombs {

    private static NamesOfCombs ourInstance = new NamesOfCombs();

    public static NamesOfCombs getInstance() {
        return ourInstance;
    }

    private HashMap<String, TypeCombination> as = new HashMap<>();
    private final String PATH_TO_ASSOCIATION_JSON = "combination/association.json";

    final String F_COMBS = "combs";
    final String F_AS = "as";

    private NamesOfCombs() {
        JsonResource parser = new JsonResource();
        JSONObject association = parser.objectFromFile(PATH_TO_ASSOCIATION_JSON)
                .optJSONObject(F_COMBS);
        Iterator<String> it = association.keys();
        while (it.hasNext()) {
            String key = it.next();
            TypeCombination type;
            try {
                type = TypeCombination.valueOf(key);
                as.put(type.toString(), type);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                continue;
                //todo warn
            }
            for (Object objAs :
                    association.getJSONObject(key).getJSONArray(F_AS)) {
                String name = objAs.toString();
                as.put(name, type);
            }
        }
    }

    public TypeCombination getType(String name) {
        TypeCombination type = as.get(name);
        return type == null ? TypeCombination.UNKNOWN : type;
    }
}
