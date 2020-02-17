package stud.task.core.stage;

import stud.task.core.component.Game;

public interface Stage  {

    void start(Game game);

    TypeStage type();
}
