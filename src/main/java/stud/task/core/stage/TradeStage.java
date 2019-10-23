package stud.task.core.stage;

import stud.task.control.Controller;
import stud.task.core.ActionRequirements;
import stud.task.core.Bank;
import stud.task.core.GameItems;
import stud.task.core.player.Action;
import stud.task.core.player.Chooser;
import stud.task.core.player.Player;
import stud.task.domain.Response;
import stud.task.util.Container;

import java.util.LinkedList;
import java.util.List;

public class TradeStage implements Stage{

    @SuppressWarnings("unchecked")
    @Override
    public void start(GameItems gi) {
        List<Player> cPlayers = (List<Player>) gi.getItem("list_curPlayers").get();
        Controller cv = gi.getController();
        Bank b = gi.getBank();
        ActionRequirements ar = gi.getActCheck();
        ar.setUpperLimit(minPurse(cPlayers));
        LinkedList<Player> removed = new LinkedList<>();
        boolean reset;
        do {
            reset = false;
            for (Player p :
                    cPlayers) main : {
                Action a;
                Chooser ch = p.getChooser();
                while (true) {
                    a = ch.getAction();
                    if (ar.checkAction(a, p)) {
                        ch.notifyStatus(true);
                        cv.actionBy(p, a);
                        cPlayers.forEach(player -> player.getChooser().notifyState(new Response(ar.getUpperLimit(), ar.getLowerLimit(),
                                b.getBank(), player.getStorage().getSum())));
                        break;
                    } else {
                        p.getChooser().notifyStatus(false);
                    }
                    cPlayers.forEach(player -> player.getChooser().notifyState(new Response(ar.getUpperLimit(), ar.getLowerLimit(),
                            b.getBank(), player.getStorage().getSum())));
                }
                switch (a.getType()) {
                    case CALL:
                        b.setBy(p.getId(), a.getAddition());
                        break;
                    case CHECK:
                        break;
                    case RAISE:
                        reset = true;
                        b.setBy(p.getId(), a.getAddition());
                        break;
                    case FOLD:
                        if (cPlayers.size() - removed.size() == 2)
                            removed.add(p);
                        else {
                            reset = false;
                            break main;
                        }
                        break;
                    case ALL_IN:
                        b.setBy(p.getId(), a.getAddition());
                        break;
                }
                cv.message("Bank : "+b.getBank());
            }
            cPlayers.removeAll(removed);
            removed.clear();
        } while (reset);
        ar.clear();
        cPlayers.forEach(player -> player.getChooser().notifyState(new Response(ar.getUpperLimit(), ar.getLowerLimit(),
                b.getBank(), player.getStorage().getSum())));
    }

    private long minPurse(List<Player> players) {
        Container<Long> min = new Container<>(Long.MAX_VALUE);
        players.forEach(p -> {
            long pur = p.getStorage().getSum();
            if (pur < min.getValue()) min.setValue(pur);
        });
        return min.getValue();
    }
}
