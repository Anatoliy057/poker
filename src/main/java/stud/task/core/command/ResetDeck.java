package stud.task.core.command;

import com.sun.istack.internal.NotNull;
import stud.task.core.component.DeckCards;
import stud.task.core.component.Game;

public class ResetDeck implements Command {

    @NotNull
    private DeckCards deck;
    private DeckCards.DeckState state;

    @Override
    public void askGame(Game game) {
        deck = game.getDeck();
    }

    @Override
    public void execute() {
        state = deck.getState();
        deck.reset();
    }

    @Override
    public void unexecute() {
        deck.load(state);
    }
}
