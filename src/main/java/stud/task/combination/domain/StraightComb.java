package stud.task.combination.domain;

public class StraightComb extends AbstractCardComb {

    private final int max;

    public StraightComb(int max) {
        super(TypeCombination.STRAIGHT);
        this.max = max;
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        StraightComb straightComb = (StraightComb) o;
        return getMax() - straightComb.getMax();
    }

    public int getMax() {
        return max;
    }
}
