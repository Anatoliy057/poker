package stud.task.util;

import java.util.LinkedList;
import java.util.List;

public class FactoryObjects<T> {

    public List<T> create(List<String> names) {
        LinkedList<T> list = new LinkedList<>();
        names.forEach(n -> list.add(create(n)));
        return list;
    }

    public T create(String name) {
        try {
            return (T) Class.forName(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
