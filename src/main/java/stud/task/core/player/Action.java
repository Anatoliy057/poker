package stud.task.core.player;

import java.util.Objects;
import java.util.StringJoiner;

public class Action {

    private TypeAction type;
    private Object v;

    public Action(TypeAction type) {
        this.type = type;
    }

    public Action(TypeAction type, Object o) {
        this.type = type;
    }

    public TypeAction getType() {
        return type;
    }

    public Object getV() {
        return v;
    }

    public void setV(Object v) {
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return type == action.type &&
                Objects.equals(v, action.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, v);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Action.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("v=" + v)
                .toString();
    }
}
