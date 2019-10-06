package stud.task.combination.domain;

import stud.task.card.TypeCard;

public class TypicalComb extends AbstractCardComb {

    private int priority;

    public TypicalComb(TypeCombination type, int priority) {
        super(type);
        this.priority = priority;
    }

    public TypicalComb(TypeCombination typeComb, TypeCard typeCard) {
        this(typeComb, typeCard.getLvl());
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
