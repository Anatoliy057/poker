package stud.task.core.command;

import stud.task.core.component.Game;
import stud.task.core.component.TransactionGame;

public class ResetGame implements Command {

    private TransactionGame transaction;
    private ResetBank resetBank;
    private ResetDeck resetDeck;
    private ResetPlayers resetPlayers;
    private ResetTable resetTable;

    @Override
    public void askGame(Game game) {
        transaction = game.getTransaction();
        resetDeck = new ResetDeck();
        resetBank = new ResetBank();
        resetPlayers = new ResetPlayers();
        resetTable = new ResetTable();
        resetBank.askGame(game);
        resetDeck.askGame(game);
        resetPlayers.askGame(game);
        resetTable.askGame(game);
    }

    @Override
    public void execute() {
        resetDeck.execute();
        resetBank.execute();
        resetPlayers.execute();
        resetTable.execute();
        transaction.resetCurBet();
        transaction.updateMaxBet();
        transaction.commit();
    }

    @Override
    public void unexecute() {
        transaction.reset();
        resetTable.unexecute();
        resetPlayers.unexecute();
        resetBank.unexecute();
        resetDeck.unexecute();
    }
}
