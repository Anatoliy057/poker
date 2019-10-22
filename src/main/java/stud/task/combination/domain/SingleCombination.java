package stud.task.combination.domain;

import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.card.TypeCard;

import java.util.Collection;
import java.util.List;

public class SingleCombination extends AbstractCardComb {

    private final int priority;

    public SingleCombination(TypeCombination type, int priority, List<Card> cards) {
        super(type, cards);
        this.priority = priority;
    }

    public SingleCombination(TypeCombination type, TypeCard typeCard, List<Card> cards) {
        this(type, typeCard.getLvl(), cards);
    }

    public SingleCombination(TypeCombination type, SuitCard suitCard, List<Card> cards) {
        this(type, suitCard.getPriority(), cards);
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
