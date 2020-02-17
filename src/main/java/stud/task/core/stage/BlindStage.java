package stud.task.core.stage;

import stud.task.core.BetException;
import stud.task.core.command.DoBet;
import stud.task.core.command.PrepareTrade;
import stud.task.core.command.EndStage;
import stud.task.core.component.Game;

public class BlindStage implements Stage {

    @Override
    public void start(Game game) {
        game.doCommand(new PrepareTrade());
        try {
            game.doCommand(new DoBet(game.nextTPlayer(), game.getBlindBet(), game::checkBet));
            game.doCommand(new DoBet(game.nextTPlayer(), game.getBlindBet() * 2, game::checkBet));
        } catch (BetException e) {
            e.printStackTrace();
        }
        game.doCommand(new EndStage(TypeStage.PREFLOP));
    }

    @Override
    public TypeStage type() {
        return TypeStage.BLIND;
    }
}
