package stud.task.core.stage;

import stud.task.combination.domain.CardCombination;
import stud.task.control.Controller;
import stud.task.core.Bank;
import stud.task.core.GameItems;
import stud.task.core.player.Player;
import stud.task.core.player.Storage;

import java.util.*;

public class ShowdownStage implements Stage {
    @SuppressWarnings("unchecked")
    @Override
    public void start(GameItems gi) {
        List<Player> cPlayers = (List<Player>) gi.getItem("list_curPlayers").get();
        Controller cv = gi.getController();
        Bank b = gi.getBank();

        LinkedList<Player> winners = new LinkedList<>();
        winners.add(cPlayers.get(0));
        List<CardCombination> maxCombs = cPlayers.get(0).getCombs();
        Iterator<Player> it = cPlayers.iterator();
        it.next();
        while (it.hasNext()) {
            Player p = it.next();
            List<CardCombination> combs = p.getCombs();
            int comp = compareListCombs(maxCombs, combs);
            if (comp > 0) continue;
            else if (comp == 0) {
                winners.add(p);
            } else {
                winners.clear();
                winners.add(p);
                maxCombs = combs;
            }
        }

        cv.message("Победители :" + winners);
        HashMap<UUID, Storage> map = new HashMap<>();
        winners.forEach(w -> map.put(w.getId(), w.getStorage()));
        b.distribute(map);

        cv.message("Комбинация : " + maxCombs.get(0));

        LinkedList<Player> removed = new LinkedList<>();
        for (Player p :
                gi.getPlayers()) {
            if (p.getStorage().getSum() < 30)
                removed.add(p);
        }
        cv.message("Удаленные игроки :" + removed);
        gi.getPlayers().removeAll(removed);
    }

    private int compareListCombs(List<CardCombination> l1, List<CardCombination> l2) {
        int max = l1.size() > l2.size() ? l2.size() : l1.size();
        for (int i = 0; i < max; i++) {
            int comp = l1.get(i).compareTo(l2.get(i));
            if (comp == 0) continue;
            else return comp;
        }
        return l1.size() - l2.size();
    }
}
