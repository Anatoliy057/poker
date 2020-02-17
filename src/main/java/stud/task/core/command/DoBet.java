package stud.task.core.command;

import stud.task.control.Controller;
import stud.task.core.BetException;
import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.component.TransactionGame;
import stud.task.core.player.Player;

import java.util.function.Predicate;

public class DoBet implements Command {

    private Controller view;

    private Player player;
    private Bank bank;
    private long bet;

    private TransactionGame transaction;
    private long prevBet;

    public DoBet(Player player, long bet, Predicate<Long> checkBet) throws BetException {
        this.player = player;
        this.bet = bet;

        if (!checkBet.test(bet))
            throw new BetException(bet);
    }

    @Override
    public void askGame(Game game) {
        bank = game.getBank();
        transaction = game.getTransaction();
        view = game.getView();
    }

    @Override
    public void execute() {
        prevBet = bank.getBetBy(player.getId());
        bank.betBy(player.getId(), bet);
        transaction.updateCurBet(bet);
        transaction.commit();
        view.messageBy(player, "Делет ставку " + bet);
    }

    @Override
    public void unexecute() {
        bank.betBy(player.getId(), prevBet);
        transaction.reset();
    }
}
