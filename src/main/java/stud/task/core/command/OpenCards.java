package stud.task.core.command;

import com.google.common.collect.Maps;
import stud.task.combination.domain.CardCombination;
import stud.task.control.Controller;
import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.player.Player;
import stud.task.core.player.Storage;

import java.util.*;
import java.util.stream.Collectors;

public class OpenCards implements Command {

    private Controller view;
    private Bank bank;
    private Bank.BankState state;
    private List<Player> curPlayers;

    @Override
    public void askGame(Game game) {
        bank = game.getBank();
        curPlayers = game.getCurPlayers();
        view = game.getView();
    }

    @Override
    public void execute() {
        state = bank.getState();
        List<Map.Entry<Player, List<CardCombination>>> winner = curPlayers.stream()
                .map(p -> Maps.immutableEntry(p, p.getCombs()))
                .peek(e -> Collections.sort(e.getValue()))
                .sorted((e1, e2) -> {
                    Iterator<CardCombination> l1 = e1.getValue().iterator();
                    Iterator<CardCombination> l2 = e2.getValue().iterator();
                    int comp = 0;
                    while (comp == 0 && l1.hasNext() && l2.hasNext()) {
                        comp = l1.next().compareTo(l2.next());
                    }
                    if ( !(l1.hasNext() && l2.hasNext())) {
                        if (!(l1.hasNext() || l2.hasNext())) comp = 0;
                        else if (l1.hasNext()) comp = 1;
                        else comp = -1;
                    }
                    return comp;
                })
                .limit(1)
                .collect(Collectors.toList());
        CardCombination c = winner.get(0).getValue().get(0);
        bank.distribute(winner.stream().map(Map.Entry::getKey).collect(Collectors.toMap(Player::getId, Player::getStorage)));
        view.messageBy(winner.get(0).getKey(), "выйграл");
        view.message("Выигрывающая комбинация : " + c);
        view.message("");
    }

    @Override
    public void unexecute() {
        bank.setState(state);
    }
}
