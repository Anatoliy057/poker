package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.TypeCard;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.StraightComb;

import java.util.List;

public class StraightDeter extends AbstractCombDeter {

    public final int NUMBER_OF_CARDS = 5;

    private int max = -1;
    private int min = Integer.MAX_VALUE;
    private int currentSum = 0;
    private int count = 0;

    @Override
    public void add(Card card) {
        max = Math.max(card.level(), max);
        min = Math.min(card.level(), min);
        currentSum += card.level();
        count++;
    }

    @Override
    public List<CardCombination> get() {
        if (currentSum == 0 || count < NUMBER_OF_CARDS) {
            return empty();
        } else {
            if (currentSum == TypeCard.getSum(TypeCard.getMinLvl(), TypeCard.getMinLvl()+NUMBER_OF_CARDS-2) + TypeCard.getMaxLvl()) {
                max = TypeCard.getMinLvl()+4;
                return of(new StraightComb(max));
            }
            return max - min == NUMBER_OF_CARDS-1 ? of(new StraightComb(max)) : empty();
        }
    }
}
