package stud.task.core.stages;

import stud.task.card.Card;
import stud.task.core.GameItems;
import stud.task.core.player.Player;

import java.util.LinkedList;

public class InitTexasHoldStage implements Stage {
    @Override
    public void start(GameItems gi) {
        LinkedList<Player> listPlayers = new LinkedList<>(gi.getPlayers());
        LinkedList<Card> table = new LinkedList<>();
        Integer highBlind = 10;
        Integer smallBlind = highBlind/2;
        Double bet = 0.0;

        gi.pullItem("list_curPlayers", listPlayers);
        gi.pullItem("int_highBlind", highBlind);
        gi.pullItem("int_smallBlind", smallBlind);
        gi.pullItem("double_bet", bet);
        gi.pullItem("list_table", table);
    }
}
