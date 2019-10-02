package stud.task.card;

public enum SuitCard {

    /**
     * @param priority must be < length and > 0;
     * @param length is count objects;
     *
     * @author Ay57
     */

    DIAMONDS(3),
    HEARTS(2),
    SPADES(1),
    CLUBS(0);

    private final int priority;
    public final static int length = values().length;

    SuitCard(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public int comparePriorTo(SuitCard o) {
        return getPriority() - o.getPriority();
    }
}
