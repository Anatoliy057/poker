package stud.task.core.player;

public enum TypeAction {
    CALL(false),
    CHECK(false),
    RAISE(true),
    FOLD(false),
    ALL_IN(true),
    YES(false),
    NO(false);

    private boolean addition;

    TypeAction(boolean addition) {
        this.addition = addition;
    }

    public boolean isAddition() {
        return addition;
    }
}