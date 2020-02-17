package stud.task.core.player;

import stud.task.core.component.GameInfo;

public interface Chooser {

    Action action(Player p, GameInfo gi);

    TypeChooser getType();

    Choosers getChooser();
}
