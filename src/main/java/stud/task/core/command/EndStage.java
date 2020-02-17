package stud.task.core.command;

import stud.task.core.component.Game;
import stud.task.core.component.TransactionGame;
import stud.task.core.stage.TypeStage;

public class EndStage implements Command {

    private TypeStage next;
    private TransactionGame transaction;

    public EndStage(TypeStage next) {
        this.next = next;
    }

    @Override
    public void askGame(Game game) {
        transaction = game.getTransaction();
    }

    @Override
    public void execute() {
        transaction.updateStage(next);
        transaction.commit();
    }

    @Override
    public void unexecute() {
        transaction.reset();
    }
}
