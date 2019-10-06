package stud.task.combination.domain;

import stud.task.card.Card;

public abstract class AbstractCardComb implements CardCombination {

    private TypeCombination type;

    public AbstractCardComb(TypeCombination type) {
        this.type = type;
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
