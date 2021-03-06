package stud.task.combination.domain;

import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.card.TypeCard;

import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class DoubleCombination extends AbstractCardComb {

    private final int lvl1, lvl2;

    public DoubleCombination(TypeCombination type, int lvl1, int lvl2, List<Card> cards) {
        super(type, cards);
        this.lvl1 = lvl1;
        this.lvl2 = lvl2;
    }

    public DoubleCombination(TypeCombination type, TypeCard lvl1, TypeCard lvl2, List<Card> cards) {
        this(type, lvl1.getLvl(), lvl2.getLvl(), cards);
    }

    public DoubleCombination(TypeCombination type, SuitCard lvl1, SuitCard lvl2, List<Card> cards) {
        this(type, lvl1.getPriority(), lvl2.getPriority(), cards);
    }

    public DoubleCombination(TypeCombination type, TypeCard lvl1, SuitCard lvl2, List<Card> cards) {
        this(type, lvl1.getLvl(), lvl2.getPriority(), cards);
    }

    public DoubleCombination(TypeCombination type, SuitCard lvl1, TypeCard lvl2, List<Card> cards) {
        this(type, lvl1.getPriority(), lvl2.getLvl(), cards);
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        DoubleCombination comb = (DoubleCombination) o;
        int comp = getLvl1() - comb.getLvl1();
        if (comp == 0) {
            return getLvl2() - comb.getLvl2();
        } else
            return comp;
    }

    public int getLvl1() {
        return lvl1;
    }

    public int getLvl2() {
        return lvl2;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DoubleCombination.class.getSimpleName() + "[", "]")
                .add("cards=" + cards)
                .toString();
    }
}
