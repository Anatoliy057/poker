package stud.task.core.player;

import java.util.Objects;
import java.util.UUID;

public class SimpleStorage implements Storage {

    private UUID id;
    private double purse;

    public SimpleStorage(double purse) {
        this.purse = purse;
        id = UUID.randomUUID();
    }

    @Override
    public boolean takeAway(double sum) {
        if (purse - sum < 0)
            return false;
        else
            purse -= sum;
        return true;
    }

    @Override
    public void give(double sum) {
        purse += sum;
    }

    public double getPurse() {
        return purse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleStorage that = (SimpleStorage) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleStorage{");
        sb.append("purse=").append(purse);
        sb.append('}');
        return sb.toString();
    }
}
