package stud.task.core.stages;

import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.core.GameItems;
import stud.task.core.player.Player;
import stud.task.core.player.TypeAction;
import stud.task.core.player.TypeChooser;

import java.util.LinkedList;

public class PreFlopStage implements Stage {
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        LinkedList<Player> curPlayers = (LinkedList<Player>) gi.getItem("list_curPlayers").get();
        DeckCards deck = gi.getDeck();
        for (Player p :
                curPlayers) {
            p.addCard(deck.pullOutCard());
            p.addCard(deck.pullOutCard());
            if (p.getChooser().getType() == TypeChooser.HUMAN) {
                c.message("Подтвердите действие YES");
                while (p.getChooser().getAction().getType() != TypeAction.YES) {
                    c.message("Неправильный ввод");
                }
                c.cardPlayer(p);
            }
        }
    }
}
