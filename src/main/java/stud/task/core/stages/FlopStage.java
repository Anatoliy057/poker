package stud.task.core.stages;

import stud.task.card.Card;
import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.core.GameItems;
import stud.task.core.player.Player;

import java.util.LinkedList;

public class FlopStage implements Stage {
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        LinkedList<Player> curPlayers = (LinkedList<Player>) gi.getItem("list_curPlayers").get();
        LinkedList<Card> table = (LinkedList<Card>) gi.getItem("list_table").get();
        DeckCards deck = gi.getDeck();
        table.add(deck.pullOutCard());
        table.add(deck.pullOutCard());
        table.add(deck.pullOutCard());
        c.cardTable(table);
        for (Player p :
                curPlayers) {
            table.forEach(p::addCard);
        }
    }
}
