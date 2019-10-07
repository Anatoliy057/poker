package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.TypeCard;
import stud.task.combination.domain.*;

import java.util.*;

public class StraightFlushDeter extends AbstractCombDeter {

    private FlushDeter flushDeter;

    public StraightFlushDeter() {
        flushDeter = new FlushDeter();
    }

    @Override
    public void add(Card card) {
        flushDeter.add(card);
    }

    @Override
    public List<CardCombination> get() {
        List<CardCombination> flush = flushDeter.get();
        if (!flush.isEmpty()) {
            FlushComb flushComb = (FlushComb) flush.get(0);
            List<Integer> levels = new ArrayList<>(flushComb.getLevels());
            Collections.sort(levels);
            int size = levels.size();
            int length = levels.get(0) == TypeCard.getMinLvl()
                    && levels.get(size - 1) == TypeCard.getMaxLvl() ? 1 : 0;
            for (int i = 0; i < size - 1; i++) {
                if (levels.get(i + 1) - levels.get(i) == 1) {
                    length++;
                } else if (length >= StraightDeter.NUMBER_OF_CARDS-1) {
                    return of(new SingleCombination(TypeCombination.STRAIGHT_FLUSH, levels.get(i)));
                } else length = 0;
            }
            if (length >= StraightDeter.NUMBER_OF_CARDS-1)
                return of(royalOrStraightFlush(levels.get(size-1), flushComb.getPriority()));
        }
        return flush;
    }

    private CardCombination royalOrStraightFlush(int max, int priority) {
        if (max == TypeCard.getMaxLvl()) {
            return new SingleCombination(TypeCombination.ROYAL_FLUSH, priority);
        } else
            return new SingleCombination(TypeCombination.STRAIGHT_FLUSH, max);
    }
}
