package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.CardsSuitToType;
import stud.task.card.SuitCard;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.DoubleCombination;
import stud.task.combination.domain.SingleCombination;
import stud.task.combination.domain.TypeCombination;

import java.util.*;

public class FlushDeter extends AbstractCombDeter {

    public final static int NUMBER_OF_CARDS = 5;

    private CardsSuitToType cards;
    private int count = 0;

    public FlushDeter() {
        cards = new CardsSuitToType();
    }

    @Override
    public void add(Card card) {
        cards.add(card);
        count++;
    }

    @Override
    public CardCombination get() {
        if (count < NUMBER_OF_CARDS) return null;
        for (List<Card> l : cards) {
            if (l.size() >= NUMBER_OF_CARDS) {
                return create(l);
            }
        }
        return null;
    }

    @Override
    public void clear() {
        cards.forEach(List::clear);
        count = 0;
    }

    private CardCombination create(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::level));
        List<Card> sub = cards.subList(cards.size() - NUMBER_OF_CARDS, cards.size());
        return new DoubleCombination(
                TypeCombination.FLUSH,
                sub.get(sub.size() - 1).level(),
                sub.get(0).level(),
                sub);
    }
}
