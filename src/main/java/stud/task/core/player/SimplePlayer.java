package stud.task.core.player;

import stud.task.card.Card;
import stud.task.combination.ListComb;
import stud.task.combination.domain.CardCombination;

import java.util.*;

public class SimplePlayer implements Player{

    private Storage storage;
    private Chooser chooser;
    private DeskPlayer deskPlayer;
    private Collection<Card> cards;

    private ListComb combs;

    public SimplePlayer(Storage storage, Chooser chooser, DeskPlayer deskPlayer) {
        this.storage = storage;
        this.deskPlayer = deskPlayer;
        this.chooser = chooser;
        combs = new ListComb();
        cards = new LinkedList<>();
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public DeskPlayer getDeskPlayer() {
        return deskPlayer;
    }

    @Override
    public Chooser getChooser() {
        return chooser;
    }

    @Override
    public Collection<Card> getCards() {
        return cards;
    }

    @Override
    public boolean isCan() {
        return storage.getPurse() > 0;
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
        combs.add(card);
    }

    @Override
    public void clear() {
        combs = new ListComb();
        cards.clear();
    }

    @Override
    public List<CardCombination> getCombs() {
        return combs.getAllCombs();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePlayer that = (SimplePlayer) o;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimplePlayer{");
        sb.append("deskPlayer=").append(deskPlayer);
        sb.append('}');
        return sb.toString();
    }
}
