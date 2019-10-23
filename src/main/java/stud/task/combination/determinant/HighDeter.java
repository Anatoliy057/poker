package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.CardsTypeToSuit;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.SingleCombination;
import stud.task.combination.domain.TypeCombination;

import java.util.LinkedList;
import java.util.List;

public class HighDeter extends AbstractCombDeter {

    private CardsTypeToSuit cards = new CardsTypeToSuit();

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public CardCombination get() {
        Card max = null;
        for (List<Card> l :
                cards) {
            if (l.size() == 1) {
               Card c = l.get(0);
               if (max == null || c.compareTo(max) > 0) {
                   max = c;
               }
            }
        }
        if (max != null) {
            LinkedList<Card> l = new LinkedList<>();
            l.add(max);
            return new SingleCombination(TypeCombination.HIGH_CARD, max.level(), l);
        } else
            return null;
    }

    @Override
    public void clear() {
        cards.clear();
    }
}
