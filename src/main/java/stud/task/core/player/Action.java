package stud.task.core.player;

import java.util.Objects;
import java.util.StringJoiner;

public class Action {

    private TypeAction type;
    private Long addition;

    public Action(TypeAction type) {
        this.type = type;
    }

    public Action(TypeAction type, Long o) {
        this.type = type;
        addition = o;
    }

    public TypeAction getType() {
        return type;
    }

    public Long getAddition() {
        return addition;
    }

    public void setAddition(Long addition) {
        this.addition = addition;
    }

    public boolean hasAddition() {
        return type.isAddition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return type == action.type &&
                Objects.equals(addition, action.addition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, addition);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Action.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("addition=" + addition)
                .toString();
    }
}
