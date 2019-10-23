package stud.task.core;

import stud.task.core.player.Storage;
import stud.task.util.Container;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Bank {

    private Map<UUID, Container<Long>> participants;
    private long bank = 0;

    public Bank(Collection<UUID> ids) {
        participants = new HashMap<>();
        ids.forEach(id -> participants.put(id, new Container<>(0L)));
    }

    public long getBank() {
        return bank;
    }

    public void setBy(UUID id, long bet) {
        double oldBet = 0;
        try {
            Container<Long> c = participants.get(id);
            oldBet = c.getValue();
            c.setValue(bet);
        } catch (NullPointerException e) {
            //todo exception
        }
        increase(bet - oldBet);
    }

    public void addBy(UUID id, long pay) {
        try {
            participants.get(id).conversion(d -> d+pay);
        } catch (NullPointerException e) {
            //todo exception
        }
        increase(pay);
    }

    public long shareBy(UUID id) {
        return participants.get(id).getValue();
    }

    public void distribute(Map<UUID, Storage> winners) {
        long prize = bank/winners.size();
        AtomicReference<Storage> first = new AtomicReference<>();
        winners.forEach((id, s) -> {
            if (participants.containsKey(id)) {
                if (first.get() == null) {
                    first.set(s);
                }
                s.give(prize);
            } else {
                //todo exception
            }
        });
        long temp = bank - prize*winners.size();
        first.get().give(temp);
    }

    private void increase(double sum) {
        if (sum < 0) {
            //todo exception
        }
        bank += sum;
    }
}
