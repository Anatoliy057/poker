package stud.task.core.command;

import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.component.TransactionGame;
import stud.task.core.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PrepareTrade implements Command {

    private TransactionGame transaction;
    private Bank bank;
    private List<Player> playerList;
    private Bank.BankState state;

    @Override
    public void askGame(Game game) {
        this.transaction = game.getTransaction();
        bank = game.getBank();
        playerList = game.getCurPlayers();
    }

    @Override
    public void execute() {
        state = bank.getState();
        transaction.resetCurBet();
        transaction.updateMaxBet();
        transaction.commit();
        bank.reload(playerList.stream()
                .map(Player::getId)
                .collect(Collectors.toList())
        );
    }

    @Override
    public void unexecute() {
        bank.setState(state);
        transaction.reset();
    }
}
