package stud.task.card;

import java.util.HashMap;
import java.util.Map;

public enum SuitCard {

    DIAMONDS(3, "♦"),
    HEARTS(2, "♥"),
    SPADES(1, "♠"),
    CLUBS(0, "♣");

    private final int priority;
    private final String suit;
    public final static int length = values().length;
    private static final Map<Integer, SuitCard> map = new HashMap<>();

    static {
        for (SuitCard c :
                values()) {
            map.put(c.priority, c);
        }
    }

    SuitCard(int priority, String suit) {
        this.priority = priority;
        this.suit = suit;
    }

    public static SuitCard get(int n) {
        return map.get(n);
    }

    public int getPriority() {
        return priority;
    }

    public int comparePriorTo(SuitCard o) {
        return getPriority() - o.getPriority();
    }

    @Override
    public String toString() {
        return suit;
    }
}
