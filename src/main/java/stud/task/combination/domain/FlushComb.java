package stud.task.combination.domain;

import stud.task.card.SuitCard;
import stud.task.card.TypeCard;

public class FlushComb extends AbstractCardComb {

    private final int max;
    private final int min;
    private final int priority;

    public FlushComb(int max, int min, int priority) {
        super(TypeCombination.FLUSH);
        this.max = max;
        this.min = min;
        this.priority = priority;
    }

    public FlushComb(TypeCard max, TypeCard min, SuitCard suit) {
        this(max.getLvl(), min.getLvl(), suit.getPriority());
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        FlushComb flushComb = (FlushComb) o;
        int comp = getMax() - flushComb.getMax();
        return comp == 0 ? getMin() - flushComb.getMin() : comp;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getPriority() {
        return priority;
    }
}
