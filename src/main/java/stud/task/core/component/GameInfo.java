package stud.task.core.component;

import stud.task.card.Card;
import stud.task.control.Controller;
import stud.task.core.player.TypeAction;

import java.util.*;

public class GameInfo {

    private Set<TypeAction> possible;

    private Controller view;

    private List<Card> table;

    private long countPlayers;

    private long bet;
    private long maxBet;
    private long bank;

    public GameInfo(Game game) {
        possible = new HashSet<>();
        table = new LinkedList<>(game.getTable());
        countPlayers = game.getCurPlayers().size();
        bank = game.getBank().get();
        maxBet = game.getCurMaxBet();
        bet = game.getCurBet();
        view = game.getView();
    }

    public boolean checkBet(long bet) {
        return bet >= this.bet && bet <= maxBet;
    }

    public List<Card> getTable() {
        return table;
    }

    public long getCountPlayers() {
        return countPlayers;
    }

    public long getBet() {
        return bet;
    }

    public long getMaxBet() {
        return maxBet;
    }

    public long getBank() {
        return bank;
    }

    public void setPossible(Set<TypeAction> possible) {
        this.possible = possible;
    }

    public Set<TypeAction> getPossible() {
        return possible;
    }

    public Controller getView() {
        return view;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "table=" + table +
                ", countPlayers=" + countPlayers +
                ", bet=" + bet +
                ", maxBet=" + maxBet +
                ", bank=" + bank +
                '}';
    }
}
