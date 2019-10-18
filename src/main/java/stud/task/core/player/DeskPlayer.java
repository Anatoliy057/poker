package stud.task.core.player;

import java.util.Objects;

public class DeskPlayer {

    private String name;
    private String surname;

    public DeskPlayer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public DeskPlayer() {
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
