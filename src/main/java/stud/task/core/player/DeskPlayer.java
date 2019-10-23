package stud.task.core.player;

import org.json.JSONArray;
import org.json.JSONObject;
import stud.task.service.JsonResource;

import java.util.Objects;
import java.util.Random;

public class DeskPlayer {

    private String name;
    private String surname;

    private static JSONArray names;
    private static Random random;

    static  {
        String path = "player/names.json";

        JsonResource jr = new JsonResource();
        names = jr.arrayFromFile(path);
        random = new Random();
    }

    public DeskPlayer() {}

    public DeskPlayer(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static DeskPlayer randomInstance() {
        int index = random.nextInt(names.length());
        JSONObject name = names.getJSONObject(index);
        return new DeskPlayer(name.getString("name"),
                name.getString("surname"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeskPlayer that = (DeskPlayer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeskPlayer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
