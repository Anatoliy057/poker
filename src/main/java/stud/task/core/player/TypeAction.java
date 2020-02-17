package stud.task.core.player;

import java.util.HashMap;
import java.util.Map;

public enum TypeAction {
    CALL,
    BET,
    CHECK,
    RAISE,
    FOLD,
    ALL_IN,

    YES,
    NO,
    SAVE;

    private final static Map<String, TypeAction> mapValueOf;

    static {
        mapValueOf = new HashMap<>();
        for (TypeAction t :
                values()) {
            mapValueOf.put(t.toString().toLowerCase(), t);
        }
    }

    public static TypeAction fromString(String s) {
        return mapValueOf.get(s.toLowerCase());
    }

}
