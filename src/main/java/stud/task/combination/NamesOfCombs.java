package stud.task.combination;

import org.json.JSONObject;
import stud.task.combination.domain.TypeCombination;
import stud.task.service.JsonResource;

import java.util.HashMap;
import java.util.Iterator;

class NamesOfCombs {

    private static HashMap<String, TypeCombination> as = new HashMap<>();
    private static final String PATH_TO_ASSOCIATION_JSON = "combination/association.json";

    static {
        final String fCombs = "combs";
        final String fAs = "as";

        JsonResource parser = new JsonResource();
        JSONObject association = parser.objectFromFile(PATH_TO_ASSOCIATION_JSON)
                .optJSONObject(fCombs);
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
                    association.getJSONObject(key).getJSONArray(fAs)) {
                String name = objAs.toString();
                as.put(name, type);
            }
        }
    }

    private  NamesOfCombs() {}

    static TypeCombination getType(String name) {
        TypeCombination type = as.get(name);
        return type == null ? TypeCombination.UNKNOWN : type;
    }
}
