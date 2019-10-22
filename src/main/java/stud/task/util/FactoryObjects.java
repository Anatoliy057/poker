package stud.task.util;

import org.json.JSONArray;
import org.json.JSONObject;
import stud.task.service.JsonResource;

import java.util.*;

public class FactoryObjects<T> {

    private JsonResource resource;

    private String pref = "", post = "";
    private final String F_PREF = "prefix";
    private final String F_POST = "postfix";
    private final String F_OBJS = "objects";

    private Collection<String> names;

    public FactoryObjects() {}

    public FactoryObjects(String path) {
        resource = new JsonResource();
        JSONObject object = resource.objectFromFile(path);
        if (object.has(F_PREF)) pref = object.getString(F_PREF);
        if (object.has(F_POST)) post = object.getString(F_POST);
        if (object.has(F_OBJS)) {
            JSONArray arr = object.getJSONArray(F_OBJS);
            names = new LinkedList<>();
            arr.forEach(o -> names.add(pref + o.toString() + post));
        } else {
            //todo exception
        }
    }

    public List<T> createList(Collection<String> names) {
        LinkedList<T> list = new LinkedList<>();
        names.forEach(n -> list.add(createSingle(n)));
        return list;
    }

    @SuppressWarnings("unchecked")
    public T createSingle(String name) {
        try {
            return (T) Class.forName(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> createList() {
        if (names == null) {
            throw new NullPointerException("Names of objects is not init");
        }
        return createList(names);
    }

    public Map<String, T> createMap() {
        if (names == null) {
            throw new NullPointerException("Names of objects is not init");
        }
        return createMap(names);
    }

    public Map<String, T> createMap(Collection<String> names) {
        Map<String, T> map = new HashMap<>();
        Set<String> set = new HashSet<>(names);
        set.forEach(n -> map.put(n, createSingle(n)));
        return map;
    }
}
