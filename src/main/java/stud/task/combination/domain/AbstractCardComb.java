package stud.task.combination.domain;

import com.sun.istack.internal.NotNull;
import stud.task.card.Card;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCardComb implements CardCombination {

    private TypeCombination type;
    protected List<Card> cards;

    public AbstractCardComb(TypeCombination type, List<Card> cards) {
        this.type = type;
        this.cards = new LinkedList<>(cards);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public TypeCombination getType() {
        return type;
    }

    protected void setType(TypeCombination type) {
        this.type = type;
    }

    @Override
    public int compareTo(CardCombination o) {
        int comp = type.comparePriorityTo(o.getType());
        if (comp == 0) {
            return deepCompareTo(o);
        } else
            return comp;
    }

    protected abstract int deepCompareTo(CardCombination o);

    @Override
    public String toString() {
        return "AbstractCardComb{" +
                "type=" + type +
                '}';
    }
}
