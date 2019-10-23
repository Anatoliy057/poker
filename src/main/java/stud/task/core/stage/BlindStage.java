package stud.task.core.stage;

import stud.task.control.Controller;
import stud.task.core.GameItems;
import stud.task.core.player.Action;
import stud.task.core.player.Player;
import stud.task.core.player.TypeAction;

import java.util.LinkedList;
import java.util.Optional;

public class BlindStage implements Stage {

    @SuppressWarnings("unchecked")
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        Long highBlind = (Long) gi.getItem("int_highBlind").get();
        Optional<Object> optPlayers = gi.getItem("list_curPlayers");
        LinkedList<Player> curPlayers = (LinkedList<Player>) optPlayers.get();
        Player p = curPlayers.getFirst();
        gi.getBank().setBy(p.getId(), highBlind/2);
        gi.getActCheck().checkAction(new Action(TypeAction.RAISE, highBlind/2), p);
        c.messageBy(p, "Поставил малый блайнд");
        p = curPlayers.get(1);
        gi.getBank().setBy(p.getId(), highBlind);
        gi.getActCheck().checkAction(new Action(TypeAction.RAISE, highBlind), p);
        c.messageBy(p, "Поставил старший блайнд");
    }
}
