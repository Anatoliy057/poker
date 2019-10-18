package stud.task.combination.domain;

import stud.task.card.Card;
import stud.task.card.TypeCard;

import java.util.Collection;
import java.util.List;

public class SingleCombination extends AbstractCardComb {

    private int priority;

    public SingleCombination(TypeCombination type, int priority) {
        super(type);
        this.priority = priority;
    }

    public SingleCombination(TypeCombination typeComb, TypeCard typeCard) {
        this(typeComb, typeCard.getLvl());
    }

    public SingleCombination(TypeCombination type, int priority, List<Card> cards) {
        this(type, priority);
        addAllCards(cards);
    }

    public SingleCombination(TypeCombination typeComb, TypeCard typeCard, List<Card> cards) {
        this(typeComb, typeCard.getLvl(), cards);
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
