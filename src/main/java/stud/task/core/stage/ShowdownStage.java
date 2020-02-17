package stud.task.core.stage;

import stud.task.core.command.*;
import stud.task.core.component.Game;

public class ShowdownStage implements Stage {

    @Override
    public void start(Game game) {
        game.doCommand(new OpenCards());
        game.doCommand(new RemovePlayers());
        game.doCommand(new ResetGame());
        game.doCommand(new EndStage(TypeStage.BLIND));
    }

    @Override
    public TypeStage type() {
        return TypeStage.SHOWDOWN;
    }
}
