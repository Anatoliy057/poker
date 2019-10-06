package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.TypeCard;
import stud.task.combination.domain.*;

import java.util.List;
import java.util.Optional;

public class StraightFlushDeter extends AbstractCombDeter {

    private FlushDeter flushFactory;

    public StraightFlushDeter() {
        flushFactory = new FlushDeter();
    }

    @Override
    public void add(Card card) {
        flushFactory.add(card);
    }

    @Override
    public List<CardCombination> get() {
        Optional<CardCombination> opt = single(flushFactory.get());
        if (opt.isPresent()) {
            FlushComb flushComb = (FlushComb) opt.get();
            if (flushComb.getMax() - flushComb.getMin() < FlushDeter.NUMBER_OF_CARDS) {
                return of(royalOrStraightFlush(flushComb));
            } else {
                return of(flushComb);
            }
        } else return empty();
    }

    private CardCombination royalOrStraightFlush(FlushComb flushComb) {
        if (flushComb.getMin() == TypeCard.getMaxLvl()- FlushDeter.NUMBER_OF_CARDS + 1) {
            return new TypicalComb(TypeCombination.ROYAL_FLUSH, flushComb.getPriority());
        } else
            return new TypicalComb(TypeCombination.STRAIGHT_FLUSH, flushComb.getMax());
    }
}
