package stud.task.core.stage;

import stud.task.core.command.AddCardTooPlayers;
import stud.task.core.command.Command;
import stud.task.core.command.EndStage;
import stud.task.core.component.Game;

public class PreFlopStage implements Stage {

    @Override
    public void start(Game game) {
        Command command = new AddCardTooPlayers(2);
        game.doCommand(command);
        game.doCommand(new EndStage(TypeStage.TRADE));
    }

    @Override
    public TypeStage type() {
        return TypeStage.PREFLOP;
    }
}
