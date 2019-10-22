package stud.task.core.player;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class Storage {

    private long purse;

    public Storage(long purse) {
        this.purse = purse;
    }

    private Storage(UUID id, long purse) {
        this.purse = purse;
    }

    public boolean pull(long sum) {
        if (purse - sum < 0 && sum > 0)
            return false;
        else
            purse -= sum;
        return true;
    }

    public void give(long sum) {
        purse += sum;
    }

    public double getSum() {
        return purse;
    }

    public long pullAll() {
        long temp = purse;
        purse = 0;
        return temp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Storage.class.getSimpleName() + "[", "]")
                .add("purse=" + purse)
                .toString();
    }
}
