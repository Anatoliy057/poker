package stud.task.combination;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import stud.task.combination.domain.TypeCombination;
import stud.task.service.JsonResource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NamesOfCombsTest {

    @Test
    void verificationWithSource() {
        JsonResource json = new JsonResource();
        NamesOfCombs namesOfCombs = NamesOfCombs.getInstance();

        JSONObject as = json.objectFromFile("combination/association.json")
                .getJSONObject(namesOfCombs.F_COMBS);

        for (TypeCombination type :
                TypeCombination.values()) {
            JSONObject comb = as.optJSONObject(type.name());
            if (comb == null) continue;

            for (Object o :
                    comb.getJSONArray(namesOfCombs.F_AS)) {
                String name = (String) o;
                assertEquals(type, namesOfCombs.getType(name));
            }
        }
    }
}