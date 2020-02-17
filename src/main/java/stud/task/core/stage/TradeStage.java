package stud.task.core.stage;

import stud.task.core.command.PrepareTrade;
import stud.task.core.command.EndStage;
import stud.task.core.command.TakeBet;
import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.component.GameInfo;
import stud.task.core.player.Action;
import stud.task.core.player.Player;
import stud.task.core.player.TypeAction;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TradeStage implements Stage{

    @Override
    public void start(Game game) {
        List<Player> party = new LinkedList<>(game.getCurPlayers());
        Bank bank = game.getBank();
        boolean[] checkers = new boolean[party.size()];
        int index = 0;
        Player t = game.getTargetPlayer();

        while ((bank.getBetBy(t.getId()) != game.getCurBet() || game.getCurBet() == 0) && !and(checkers)) {
            Set<TypeAction> possible = new HashSet<>();
            possible.add(TypeAction.SAVE);
            possible.add(TypeAction.FOLD);
            if (game.getCurBet() == 0)
                possible.add(TypeAction.CHECK);
            if (t.getStorage().getSum() >= game.getCurBet()) {
                if (t.getStorage().getSum() == game.getCurBet()) {
                    possible.add(TypeAction.CALL);
                } else {
                    possible.add(TypeAction.CALL);
                    possible.add(TypeAction.BET);
                    possible.add(TypeAction.RAISE);
                    possible.add(TypeAction.ALL_IN);
                }
            }
            GameInfo gi = game.getInfo();
            gi.setPossible(possible);
            Action action = t.getChooser().action(t, gi);
            action.getOptCommand().ifPresent(game::doCommand);
            switch (action.getType()) {
                case CHECK:
                    checkers[index] = true;
                    index = (index + 1) % checkers.length;
                    break;
                case YES:
                case NO:
                case SAVE:
                    continue;
            }
            t = game.nextTPlayer();
        }

        TypeStage type = null;

        switch(game.getPrevStage()) {
            case PREFLOP:
                type = TypeStage.FLOP;
                break;
            case FLOP:
                type = TypeStage.TURN;
                break;
            case TURN:
                type = TypeStage.RIVER;
                break;
            case RIVER:
                type = TypeStage.SHOWDOWN;
                break;
            case SHOWDOWN:
                break;
        }

        game.doCommand(new PrepareTrade());
        game.doCommand(new TakeBet(party));
        game.doCommand(new EndStage(type));
    }

    public boolean and(boolean[] arr) {
        boolean res = true;
        for (int i = 0; i < arr.length; i++) {
            res &= arr[i];
        }
        return res;
    }

    @Override
    public TypeStage type() {
        return TypeStage.TRADE;
    }
}
