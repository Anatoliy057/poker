package stud.task.control;

import stud.task.card.Card;
import stud.task.core.player.Player;

import java.util.Collection;

public interface Controller {

    void cardTable(Collection<Card> cards);

    void cardPlayer(Player p);

    void messageBy(Player p, String message);

    void message(String message);
}
