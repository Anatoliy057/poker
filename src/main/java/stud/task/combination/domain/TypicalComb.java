package stud.task.combination.domain;

public class TypicalComb extends AbstractCardComb {

    private int priority;

    public TypicalComb(TypeCombination type, int priority) {
        super(type);
        this.priority = priority;
    }

    private int getPriority() {
        return priority;
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        TypicalComb typicalComb = (TypicalComb) o;
        return getPriority() - typicalComb.getPriority();
    }
}
