package stud.task.core.stage;

import stud.task.card.Card;
import stud.task.core.GameItems;
import stud.task.core.player.Player;

import java.util.LinkedList;

public class InitTexasHoldStage implements Stage {


    @Override
    public void start(GameItems gi) {
        LinkedList<Player> listPlayers = new LinkedList<>(gi.getPlayers());
        LinkedList<Card> table = new LinkedList<>();
        Long highBlind = (long) 10;
        Long smallBlind = highBlind/2;
        Long bet = 0L;

        gi.pullItem("list_curPlayers", listPlayers);
        gi.pullItem("int_highBlind", highBlind);
        gi.pullItem("int_smallBlind", smallBlind);
        gi.pullItem("long_bet", bet);
        gi.pullItem("list_table", table);
    }
}
