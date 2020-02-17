package stud.task.core.command;

import stud.task.core.component.Game;

public interface Command {

    void askGame(Game game);

    void execute();

    void unexecute();
}
