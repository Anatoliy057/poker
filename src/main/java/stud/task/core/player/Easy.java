package stud.task.core.player;

import stud.task.core.BetException;
import stud.task.core.command.DoBet;
import stud.task.core.component.GameInfo;

public class Easy implements Chooser {
    @Override
    public Choosers getChooser() {
        return Choosers.EASY;
    }

    @Override
    public Action action(Player p, GameInfo gi) {
        Action action = new Action();
        if (gi.getPossible().contains(TypeAction.CHECK))
            action.setType(TypeAction.CHECK);
        else if (gi.checkBet(p.getStorage().getSum())) {
            action.setType(TypeAction.CALL);
            try {
                action.setOptCommand(new DoBet(p, gi.getBet(), gi::checkBet));
            } catch (BetException e) {
                //ignored
            }
        } else {
            action.setType(TypeAction.FOLD);
        }
        return action;
    }

    @Override
    public TypeChooser getType() {
        return TypeChooser.AI;
    }
}
