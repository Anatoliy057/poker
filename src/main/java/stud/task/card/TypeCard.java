package stud.task.card;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public enum TypeCard {

    TWO(0, "2"),
    THREE(1, "3"),
    FOUR(2, "4"),
    FIVE(3, "5"),
    SIX(4, "6"),
    SEVEN(5, "7"),
    EIGHT(6, "8"),
    NINE(7, "9"),
    TEN(8, "10"),
    JACK(9, "J"),
    QUEEN(10, "Q"),
    KING(11, "K"),
    ACE(12, "A");

    private final int lvl;
    private final String type;
    public final static int length = values().length;
    private static final Map<Integer, TypeCard> map = new HashMap<>();

    static {
        for (TypeCard c :
                values()) {
            map.put(c.lvl, c);
        }
    }

    TypeCard(int lvl, String type){
        this.lvl = lvl;
        this.type = type;
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

    @Override
    public String toString() {
        return type;
    }
}
