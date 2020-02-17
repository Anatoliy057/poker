package stud.task.core.command;

import stud.task.card.Card;
import stud.task.control.Controller;
import stud.task.core.component.DeckCards;
import stud.task.core.component.Game;
import stud.task.core.player.Player;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class AddCardToTable implements Command {

    private int count;
    private DeckCards deck;
    private List<Card> table;
    private List<Player> players;

    private Controller view;

    private List<Card> cards;

    public AddCardToTable(int count) {
        this.count = count;
    }

    @Override
    public void askGame(Game game) {
        deck = game.getDeck();
        players = game.getPlayers();
        table = game.getTable();
        view = game.getView();
    }

    @Override
    public void execute() {
        cards = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            Card card = deck.poll();
            table.add(card);
            players.forEach(p -> p.addCard(card));
            cards.add(card);
        }
        view.cardTable(table);
    }

    @Override
    public void unexecute() {
        cards.forEach(deck::push);
        table.removeAll(cards);
        players.forEach(p -> p.removeCardAll(cards));
        cards.clear();
    }
}
