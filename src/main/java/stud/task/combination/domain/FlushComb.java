package stud.task.combination.domain;

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
