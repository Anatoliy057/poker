package stud.task.combination.domain;

import stud.task.card.TypeCard;

public class SingleCombination extends AbstractCardComb {

    private int priority;

    public SingleCombination(TypeCombination type, int priority) {
        super(type);
        this.priority = priority;
    }

    public SingleCombination(TypeCombination typeComb, TypeCard typeCard) {
        this(typeComb, typeCard.getLvl());
    }

    public int getPriority() {
        return priority;
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        SingleCombination singleCombination = (SingleCombination) o;
        return getPriority() - singleCombination.getPriority();
    }

    @Override
    public String toString() {
        return "SingleCombination{" +
                "priority=" + priority +
                "} " + super.toString();
    }
}
