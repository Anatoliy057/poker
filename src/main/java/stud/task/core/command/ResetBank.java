package stud.task.core.command;

import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.player.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResetBank implements Command {

    private Collection<UUID> ids;
    private Bank bank;
    private Bank.BankState state;

    @Override
    public void askGame(Game game) {
        bank = game.getBank();
        ids = game.getCurPlayers().stream()
                .map(Player::getId)
                .collect(Collectors.toList());
    }

    @Override
    public void execute() {
        state = bank.getState();
        bank.reload(ids);
    }

    @Override
    public void unexecute() {
        bank.setState(state);
    }
}
