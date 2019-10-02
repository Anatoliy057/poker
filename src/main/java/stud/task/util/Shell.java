package stud.task.util;

import stud.task.combination.domain.CardCombination;

import java.util.Objects;
import java.util.function.Function;

public class Shell<T> {

    private T value;

    public Shell() {}

    public Shell(T value) {
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
