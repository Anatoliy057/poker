package stud.task.combination.domain;

public enum TypeCombination {
    ROYAL_FLUSH(10),
    STRAIGHT_FLUSH(9),
    FOUR_KIND(8),
    FULL_HOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE_KIND(4),
    TWO_PAIR(3),
    PAIR(2),
    HIGH_CARD(1),
    UNKNOWN(0);

    private final int priority;
    public final static int length = values().length;


    TypeCombination(int priority) {
        this.priority = priority;
    }

    public int priority() {
        return priority;
    }

    public int comparePriorityTo(TypeCombination o) {
        return priority - o.priority();
    }
}
