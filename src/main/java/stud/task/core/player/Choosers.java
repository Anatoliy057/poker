package stud.task.core.player;

import java.util.EnumMap;

public enum Choosers {
    EASY,
    CONSOLE;

    private static EnumMap<Choosers, Chooser> map;

    static {
        map = new EnumMap<>(Choosers.class);
        map.put(EASY, new Easy());
        map.put(CONSOLE, new ConsoleChooser());
    }

    public Chooser getChooser() {
        return map.get(this);
    }
}
