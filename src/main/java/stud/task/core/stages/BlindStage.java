package stud.task.core.stages;

import stud.task.control.Controller;
import stud.task.core.GameItems;
import stud.task.core.player.Player;

import java.util.LinkedList;
import java.util.Optional;

public class BlindStage implements Stage {
    @Override
    public void start(GameItems gi) {
        Controller c = gi.getController();
        Integer smallBlind = (Integer) gi.getItem("int_smallBlind").get();
        Integer highBlind = (Integer) gi.getItem("int_highBlind").get();
        Optional<Object> optPlayers = gi.getItem("queue_curPlayers");
        LinkedList<Player> curPlayers = (LinkedList<Player>) optPlayers.get();
        Player p = curPlayers.getFirst();
        while (p.getStorage().takeAway(smallBlind)) {
            curPlayers.removeFirst();
            gi.getPlayers().remove(p);
            p = curPlayers.getFirst();
        }
        c.messageBy(p, "Поставил малый блайнд");
        p = curPlayers.get(1);
        while (p.getStorage().takeAway(highBlind)) {
            curPlayers.remove(1);
            gi.getPlayers().remove(p);
            p = curPlayers.get(1);
        }
        c.messageBy(p, "Поставил старший блайнд");
    }
}
