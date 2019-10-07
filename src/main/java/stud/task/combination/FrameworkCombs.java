package stud.task.combination;

import org.json.JSONArray;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.service.JsonResource;
import stud.task.util.Shell;

import java.util.HashMap;
import java.util.Map;

public class FrameworkCombs {

    private static FrameworkCombs ourInstance = new FrameworkCombs();

    public static FrameworkCombs getInstance() {
        return ourInstance;
    }

    private static final String PATH_TO_SUBSTITUTION_JSON = "combination/substitution.json";

    private TypeCombination[][] src;
    private NamesOfCombs namesOfCombs = NamesOfCombs.getInstance();

    private FrameworkCombs() {
        JsonResource parser = new JsonResource();
        JSONArray subst = parser.arrayFromFile(PATH_TO_SUBSTITUTION_JSON);
        src = new TypeCombination[subst.length()][];
        for (int i = 0; i < subst.length(); i++) {
            JSONArray arr = subst.optJSONArray(i);
            src[i] = new TypeCombination[arr.length()];
            for (int j = 0; j < arr.length(); j++) {
                String name = arr.getString(j);
                src[i][j] = namesOfCombs.getType(name);
            }
        }
    }

    public Map<TypeCombination, Shell<CardCombination>> get() {
        HashMap<TypeCombination, Shell<CardCombination>> subst = new HashMap<>();
        for (TypeCombination[] typeCombinations : src) {
            Shell<CardCombination> shell = new Shell<>();
            for (TypeCombination typeCombination : typeCombinations) {
                subst.put(typeCombination, shell);
            }
        }
        return subst;
    }

}
