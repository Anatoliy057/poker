package stud.task.combination.domain;

import com.sun.istack.internal.NotNull;
import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.card.TypeCard;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class FlushComb extends AbstractCardComb {

    private HashSet<Integer> set;
    private final int priority;

    public FlushComb(HashSet<Integer> set, int priority) {
        super(TypeCombination.FLUSH);
        this.set = set;
        this.priority = priority;
    }

    FlushComb(int max, int min, int priority) {
        super(TypeCombination.FLUSH);
        set = new HashSet<>();
        set.add(max);
        set.add(min);
        this.priority = priority;
    }

    FlushComb(TypeCard max, TypeCard min, SuitCard suit) {
        this(max.getLvl(), min.getLvl(), suit.getPriority());
    }

    FlushComb(int max, int min, int priority, List<Card> cards) {
        this(max, min, priority);
        addAllCards(cards);

    }

    FlushComb(TypeCard max, TypeCard min, SuitCard suit, List<Card> cards) {
        this(max.getLvl(), min.getLvl(), suit.getPriority());
        addAllCards(cards);
    }

    public FlushComb(HashSet<Integer> set, int priority, List<Card> cards) {
        this(set, priority);
        addAllCards(cards);
    }

    @Override
    public boolean addCard(Card card) {
        return super.addCard(card);
    }

    @Override
    public boolean addAllCards(List<Card> cards) {
        boolean b =  super.addAllCards(cards);
        Collections.sort(super.cards);
        return b;
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        FlushComb flushComb = (FlushComb) o;
        int comp = getMax() - flushComb.getMax();
        return comp == 0 ? getMin() - flushComb.getMin() : comp;
    }

    public HashSet<Integer> getLevels() {
        return set;
    }

    public int getPriority() {
        return priority;
    }

    public int getMax() {
        return Collections.max(set);
    }

    public int getMin() {
        return Collections.min(set);
    }
}
