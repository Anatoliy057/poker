package stud.task.core;

import stud.task.core.player.Action;
import stud.task.core.player.Player;
import stud.task.util.Container;

import java.util.HashMap;
import java.util.UUID;

public class ActionRequirements {

    private long lowerLimit = 0;
    private long upperLimit = Long.MAX_VALUE;

    private HashMap<UUID, Container<Long>> map = new HashMap<>();

    public ActionRequirements() {}

    public boolean checkAction(Action a, Player p) {
        if (a.hasAddition()) {
            Long bet = a.getAddition();
            if (lowerLimit <= bet && upperLimit >= bet && bet <= p.getStorage().getSum()) {
                Container<Long> c = map.get(p.getId());
                if (c == null) {
                    p.getStorage().pull(bet);
                    map.put(p.getId(), new Container<>(bet));
                } else {
                    p.getStorage().pull(bet - c.getValue());
                    c.conversion(l -> bet);
                }
                lowerLimit = bet;
                return true;
            } else
                return false;
        } else {
            switch (a.getType()) {
                case FOLD:
                    return true;
                case YES:
                case NO:
                    return false;
            }
            return lowerLimit == 0;
        }
    }

    public long getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(long lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public long getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(long upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void clear() {
        lowerLimit = 0;
        upperLimit = Long.MAX_VALUE;
        map.clear();
    }
}
