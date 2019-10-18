package stud.task.card;

import java.util.HashMap;
import java.util.Map;

public enum TypeCard {

    /**
     * @param lvl must be < length and > 0;
     * @param length is count objects;
     *
     * @author Ay57
     */

    TWO(0),
    THREE(1),
    FOUR(2),
    FIVE(3),
    SIX(4),
    SEVEN(5),
    EIGHT(6),
    NINE(7),
    TEN(8),
    JACK(9),
    QUEEN(10),
    KING(11),
    ACE(12);

    private final int lvl;
    public final static int length = values().length;
    private static final Map<Integer, TypeCard> map = new HashMap<>();

    static {
        for (TypeCard c :
                values()) {
            map.put(c.lvl, c);
        }
    }

    TypeCard(int lvl){
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    public static TypeCard get(int n) {
        return map.get(n);
    }

    public int compareLvlTo(TypeCard o) {
        return getLvl() - o.getLvl();
    }

    public static int getMaxLvl() {
        return ACE.getLvl();
    }

    public static int getMinLvl() {
        return TWO.getLvl();
    }

    public static int getSum(TypeCard start, TypeCard end) {
        if (start.getLvl() > end.getLvl()) return -1;
        int sum = 0;
        for (int i = start.getLvl(); i <= end.getLvl(); i++) {
            sum += i;
        }
        return sum;
    }

    public static int getSum(int start, int end) {
        if (start > end) return -1;
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }

}
