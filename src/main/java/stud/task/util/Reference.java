package stud.task.util;

import java.util.function.Function;

public class Reference<T> {

    private T value;

    public Reference() {}

    public Reference(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void func(Function<T, T> f) {
        value = f.apply(value);
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }
}
