package stud.task.core.stage;

import stud.task.card.Card;
import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.core.GameItems;
import stud.task.core.player.Player;

import java.util.LinkedList;

public class FlopStage implements Stage {

    @SuppressWarnings("unchecked")
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        LinkedList<Player> curPlayers = (LinkedList<Player>) gi.getItem("list_curPlayers").get();
        LinkedList<Card> table = (LinkedList<Card>) gi.getItem("list_table").get();
        DeckCards deck = gi.getDeck();
        LinkedList<Card> temp = new LinkedList<>();
        temp.add(deck.pullOutCard());
        temp.add(deck.pullOutCard());
        temp.add(deck.pullOutCard());
        table.addAll(temp);
        c.cardTable(table);
        for (Player p :
                curPlayers) {
            temp.forEach(p::addCard);
        }
    }
}
