package stud.task.card;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

public class Card implements Comparable<Card> {

    @NotNull
    private TypeCard type;
    @NotNull
    private SuitCard suit;

    public Card(TypeCard type, SuitCard suit) {
        this.type = type;
        this.suit = suit;
    }

    public TypeCard getType() {
        return type;
    }

    public SuitCard getSuit() {
        return suit;
    }

    public int level() {
        return type.getLvl();
    }

    public int priority() {
        return suit.getPriority();
    }

    @Override
    public int compareTo(Card o) {
        int comp = getType().compareLvlTo(o.getType());
        if (comp == 0) {
            return getSuit().comparePriorTo(o.getSuit());
        } else return comp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type == card.type &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, suit);
    }

    @Override
    public String toString() {
        return suit + ":" + type;
    }
}
