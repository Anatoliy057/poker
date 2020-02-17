package stud.task.core.stage;

import stud.task.core.command.AddCardToTable;
import stud.task.core.command.Command;
import stud.task.core.command.EndStage;
import stud.task.core.component.Game;

public class TurnStage implements Stage {

    @Override
    public void start(Game game) {
        Command command = new AddCardToTable(1);
        game.doCommand(command);
        game.doCommand(new EndStage(TypeStage.TRADE));
    }

    @Override
    public TypeStage type() {
        return TypeStage.TURN;
    }
}
