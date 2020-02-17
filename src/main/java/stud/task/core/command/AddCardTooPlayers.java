package stud.task.core.command;

import stud.task.card.Card;
import stud.task.control.Controller;
import stud.task.core.component.DeckCards;
import stud.task.core.component.Game;
import stud.task.core.player.Player;
import stud.task.core.player.TypeChooser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AddCardTooPlayers implements Command {

    private int count;
    private DeckCards deck;
    private List<Player> players;

    private List<Card> cards;

    public AddCardTooPlayers(int count) {
        this.count = count;
    }

    @Override
    public void askGame(Game game) {
        deck = game.getDeck();
        players = game.getPlayers();
    }

    @Override
    public void execute() {
        cards = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Card card = deck.poll();
            players.forEach(p ->  {
                p.addPlayerCard(card);
            });
            cards.add(card);
        }
    }

    @Override
    public void unexecute() {
        cards.forEach(deck::push);
        players.forEach(p -> p.removePlayerCardAll(cards));
        cards.clear();
    }
}
