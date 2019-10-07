package stud.task.combination.domain;

import stud.task.card.SuitCard;
import stud.task.card.TypeCard;

import java.util.Collections;
import java.util.HashSet;

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
        super(TypeCombination.FLUSH);
        set = new HashSet<>();
        set.add(max.getLvl());
        set.add(min.getLvl());
        this.priority = suit.getPriority();
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
