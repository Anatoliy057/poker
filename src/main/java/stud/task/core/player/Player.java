package stud.task.core.player;

import stud.task.card.Card;
import stud.task.combination.ListComb;
import stud.task.combination.determinant.CombDeter;
import stud.task.combination.domain.CardCombination;

import java.util.*;

public class Player {

    private UUID id;
    private Storage storage;
    private Chooser chooser;
    private DeskPlayer deskPlayer;
    private Collection<Card> cards;

    private ListComb combs;

    public Player(Storage storage, Chooser chooser, DeskPlayer deskPlayer, List<CombDeter> deters) {
        this.storage = storage;
        this.deskPlayer = deskPlayer;
        this.chooser = chooser;
        combs = new ListComb(deters);
        cards = new LinkedList<>();
        id = UUID.randomUUID();
    }

    public Player(Storage storage, Chooser chooser, List<CombDeter> deters) {
        this.storage = storage;
        this.chooser = chooser;
        combs = new ListComb(deters);
        cards = new LinkedList<>();
        id = UUID.randomUUID();
        deskPlayer = DeskPlayer.randomInstance();
    }

    public Storage getStorage() {
        return storage;
    }

    public DeskPlayer getDeskPlayer() {
        return deskPlayer;
    }

    public Chooser getChooser() {
        return chooser;
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public UUID getId() {
        return id;
    }

    public void addCard(Card card) {
        cards.add(card);
        combs.add(card);
    }

    public List<CardCombination> getCombs() {
        return combs.getAllCombs();
    }

    public void clear() {
        combs.clear();
        cards.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("deskPlayer=").append(deskPlayer);
        sb.append('}');
        return sb.toString();
    }
}
