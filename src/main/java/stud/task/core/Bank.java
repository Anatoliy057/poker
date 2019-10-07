package stud.task.core;

import javafx.util.Pair;
import stud.task.core.player.Storage;
import stud.task.util.Shell;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<Storage, Shell<Double>> participants;
    private double bank;

    public Bank(List<Storage> players) {
        bank = 0;
        participants = new HashMap<>();
        for (Storage s :
                players) {
            participants.put(s, new Shell<>(0.0));
        }
    }

    public boolean putBy(Storage s, double pay) {
        Shell<Double> shell = participants.get(s);
        if (shell == null) {
            //todo error
            return false;
        }
        boolean res = s.takeAway(pay);
        if (!res) return false;
        bank += pay;
        shell.func(p -> p+=pay);
        return true;
    }

    public void bankTaking(List<Storage> winners) {
        LinkedList<Pair<Storage, Double>> percents = new LinkedList<>();
        double oldBank = bank;
        double sumPercent = 0;
        double sumBet = 0;
        for (Storage s :
                winners) {
            Shell<Double> shell = participants.get(s);
            if (shell == null) {
                //todo error
                return;
            }
            double bet = shell.getValue();
            double percent = bet / bank;
            sumPercent += percent;
            percents.add(new Pair<>(s, percent));
            s.give(bet);
            sumBet += bet;
        }
        bank -= sumBet;
        while (bank >= 0) {
            double cSum = 0;
            boolean ceil = false, floor = false;
            for (Pair<Storage, Double> s :
                    percents) {
                double prize = bank * (s.getValue() / sumPercent);
                if (s.getValue() >= oldBank / participants.size()) {
                    prize = Math.ceil(prize);
                    if (prize < 0.5) ceil = true;
                } else {
                    prize = Math.floor(prize);
                    if(prize < 1) floor = true;
                }
                cSum += prize;
                s.getKey().give(prize);
            }
            bank -= cSum;
            if (ceil || floor) break;
        }
    }
}
