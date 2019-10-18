package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.TypeCard;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.combination.domain.SingleCombination;

import java.util.*;

public class StraightDeter extends AbstractCombDeter {

    public static final int NUMBER_OF_CARDS = 5;

    private HashSet<Integer> levels = new HashSet<>();
    private List<Card> uniqueCards = new ArrayList<>();
    private int count = 0;

    @Override
    public void add(Card card) {
        if (levels.add(card.level())) {
            count++;
            uniqueCards.add(card);
            Collections.sort(uniqueCards);
        }
    }

    @Override
    public List<CardCombination> get() {
        if (count < NUMBER_OF_CARDS) return empty();
        List<Integer> arr = new ArrayList<>(levels);
        Collections.sort(arr);
        int size = arr.size();
        int length = arr.get(size-1) == TypeCard.getMaxLvl() &&
                arr.get(0) == TypeCard.getMinLvl() ? 1 : 0;
        for (int i = 0; i < size-1; i++) {
            if (arr.get(i+1) - arr.get(i) == 1) {
                length++;
            } else if (length >= NUMBER_OF_CARDS-1) {
                return of(create(arr.get(i)));
            } else {
                length = 0;
            }
        }
        if (length >= NUMBER_OF_CARDS-1)
            return of(create(arr.get(size-1)));
        else
            return empty();
    }

    private CardCombination create(int max) {
        for (int i = 0; i < uniqueCards.size(); i++) {
            if (uniqueCards.get(i).level() == max) {
                return new SingleCombination(TypeCombination.STRAIGHT, max, uniqueCards.subList(i-5, i));
            }
        }
        return null;
    }
}
