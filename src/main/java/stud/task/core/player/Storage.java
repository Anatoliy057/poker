package stud.task.core.player;

import java.util.StringJoiner;
import java.util.UUID;

public class Storage {

    private long purse;

    public Storage(long purse) {
        this.purse = purse;
    }

    public boolean take(long sum) {
        if (purse - sum < 0 && sum > 0)
            return false;
        else
            purse -= sum;
        return true;
    }

    public void pull(long sum) {
        purse += sum;
    }

    public long getSum() {
        return purse;
    }

    public long takeAll() {
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
