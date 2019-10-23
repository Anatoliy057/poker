package stud.task.core.stage;

import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.core.ActionRequirements;
import stud.task.core.Bank;
import stud.task.core.GameItems;
import stud.task.core.player.Player;
import stud.task.core.player.TypeAction;
import stud.task.core.player.TypeChooser;
import stud.task.domain.Response;

import java.util.LinkedList;

public class PreFlopStage implements Stage {

    @SuppressWarnings("unchecked")
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        ActionRequirements ar = gi.getActCheck();
        Bank b = gi.getBank();
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
            Response response = new Response(ar.getUpperLimit(), ar.getLowerLimit(),
                    b.getBank(), p.getStorage().getSum());
            p.getChooser().notifyState(response);
        }
    }
}
