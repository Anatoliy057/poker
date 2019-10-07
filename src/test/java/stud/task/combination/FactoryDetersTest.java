package stud.task.combination;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import stud.task.combination.determinant.CombDeter;
import stud.task.service.JsonResource;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FactoryDetersTest {

    private FactoryDeters factory = FactoryDeters.getInstance();
    private JsonResource json = new JsonResource();

    @Test
    void expectDetersIndicatedInFile() {
        List<CombDeter> listDeters = factory.create();

        JSONObject deters = json.objectFromFile("combination/deters.json");
        String pref = deters.getString("prefix");
        String post = deters.getString("postfix");

        List<String> names = new LinkedList<>();
        for (Object o :
                deters.getJSONArray("deters")) {
            String name = (String) o;
            names.add(pref + name + post);
        }

        for (CombDeter deter :
                listDeters) {
            assertTrue(names.contains(deter.getClass().getName()));
        }
    }
}