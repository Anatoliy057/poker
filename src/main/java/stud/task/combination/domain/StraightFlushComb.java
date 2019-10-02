package stud.task.combination.domain;

public class StraightFlushComb extends AbstractCardComb {

    private int level;

    public StraightFlushComb(int level) {
        super(TypeCombination.ROYAL_FLUSH);
        this.level = level;
    }

    private int getLevel() {
        return level;
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        StraightFlushComb straightFlushComb = (StraightFlushComb) o;
        return getLevel() - straightFlushComb.getLevel();
    }
}
