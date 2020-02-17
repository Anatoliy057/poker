package stud.task.core.command;

import stud.task.card.Card;
import stud.task.core.component.Game;

import java.util.LinkedList;
import java.util.List;

public class ResetTable implements Command {

    private List<Card> oldTable;
    private List<Card> table;

    @Override
    public void askGame(Game game) {
        table = game.getTable();
    }

    @Override
    public void execute() {
        oldTable = new LinkedList<>(table);
        table.clear();
    }

    @Override
    public void unexecute() {
        table.clear();
        table.addAll(oldTable);
    }
}