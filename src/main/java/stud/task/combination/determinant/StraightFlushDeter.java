package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.card.TypeCard;
import stud.task.combination.domain.*;

import java.util.*;

public class StraightFlushDeter extends AbstractCombDeter {

    private StraightDeter straightDeter;
    private List<List<Card>> cards;

    public StraightFlushDeter() {
        straightDeter = new StraightDeter();
        cards = new ArrayList<>();
        for (int i = 0; i < SuitCard.length; i++) {
            cards.add(new LinkedList<>());
        }
    }

    @Override
    public void add(Card card) {
        cards.get(card.priority()).add(card);
    }

    @Override
    public CardCombination get() {
        cards.sort(Comparator.comparingInt(
                l -> l.isEmpty() ? 1 : -l.get(0).priority()));
        CardCombination max = new SingleCombination(TypeCombination.UNKNOWN, 0, new LinkedList<>());
        for (List<Card> l :
                cards) {
            if (l.isEmpty() || l.size() < StraightDeter.NUMBER_OF_CARDS)
                continue;
            straightDeter.clear();
            straightDeter.addAll(l);
            Optional<CardCombination> opt = straightDeter.getOpt();
            if (opt.isPresent()) {
                CardCombination comb = royalOrStraightFlush(
                        (SingleCombination) opt.get()
                );
                if (Objects.requireNonNull(comb).compareTo(max) > 0)
                    max = comb;
            }
        }
        return max.getType() == TypeCombination.UNKNOWN ? null : max;
    }

    @Override
    public void clear() {
        straightDeter.clear();
        cards.forEach(List::clear);
    }

    private CardCombination royalOrStraightFlush(SingleCombination comb) {
        if (comb.getType() != TypeCombination.STRAIGHT) return null;
        List<Card> cards = comb.getCards();
        if (comb.getPriority() == TypeCard.getMaxLvl())
            return new SingleCombination(
                    TypeCombination.ROYAL_FLUSH,
                    cards.get(0).priority(),
                    cards
            );
        else
            return new SingleCombination(
                    TypeCombination.STRAIGHT_FLUSH,
                    comb.getPriority(),
                    cards
            );
    }
}
