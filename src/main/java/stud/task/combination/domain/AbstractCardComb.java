package stud.task.combination.domain;

import com.sun.istack.internal.NotNull;
import stud.task.card.Card;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCardComb implements CardCombination {

    private TypeCombination type;
    protected List<Card> cards;

    public AbstractCardComb(TypeCombination type) {
        this.type = type;
        cards = new LinkedList<>();
    }

    @Override
    public Collection<Card> getCards() {
        return cards;
    }

    @Override
    public TypeCombination getType() {
        return type;
    }

    protected void setType(TypeCombination type) {
        this.type = type;
    }

    public boolean addCard(@NotNull Card card) {
        return cards.add(card);
    }

    public boolean addAllCards(@NotNull List<Card> cards) {
        return this.cards.addAll(cards);
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
