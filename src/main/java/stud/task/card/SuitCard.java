package stud.task.card;

import java.util.HashMap;
import java.util.Map;

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
    private static final Map<Integer, SuitCard> map = new HashMap<>();

    static {
        for (SuitCard c :
                values()) {
            map.put(c.priority, c);
        }
    }

    SuitCard(int priority) {
        this.priority = priority;
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
}
