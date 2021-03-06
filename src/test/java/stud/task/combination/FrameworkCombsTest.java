package stud.task.combination;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.service.JsonResource;
import stud.task.util.Reference;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FrameworkCombsTest {

    @Test
    void verificationWithSource() {
        NamesOfCombs namesOfCombs = NamesOfCombs.getInstance();
        FrameworkCombs frameworkCombs = FrameworkCombs.getInstance();
        Map<TypeCombination, Reference<CardCombination>> map = frameworkCombs.get();
        JsonResource json = new JsonResource();
        JSONArray substr = json.arrayFromFile("combination/substitution.json");
        for (Object o :
                substr) {
            Reference<CardCombination> sh = null;
            for (Object str :
                    (JSONArray) o) {
                String name = (String) str;
                if (sh == null)
                    sh = map.get(namesOfCombs.getType(name));
                assertSame(sh, map.get(namesOfCombs.getType(name)));
            }
        }
    }
}