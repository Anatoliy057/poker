package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.TypeCard;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.combination.domain.SingleCombination;

import java.util.*;

public class StraightDeter extends AbstractCombDeter {

    public static final int NUMBER_OF_CARDS = 5;

    private HashSet<Integer> setLevels = new HashSet<>();
    private HashMap<Integer, Card> cardsToLvl = new HashMap<>();
    private int count = 0;

    @Override
    public void add(Card card) {
        if (setLevels.add(card.level())) {
            count++;
            cardsToLvl.put(card.level(), card);
        }
    }

    @Override
    public CardCombination get() {
        if (count < NUMBER_OF_CARDS) return null;

        ArrayList<Integer> levels = new ArrayList<>(setLevels);
        Collections.sort(levels);

        int start = levels.get(0);
        int length = 1;
        int cstart = levels.get(0);
        int clength = 1;

        for (int i = 0; i < levels.size() - 1; i++) {
            if (levels.get(i + 1) - levels.get(i) == 1) {
                clength++;
            } else {
                if (clength > length) {
                    length = clength;
                    start = cstart;
                }
                clength = 1;
                cstart = levels.get(i + 1);
            }
        }

        if (clength > length) {
            length = clength;
            start = cstart;
        }

        if (length >= NUMBER_OF_CARDS) {
            start -= length - NUMBER_OF_CARDS;
            LinkedList<Card> cards = new LinkedList<>();
            for (int i = start; i < start + NUMBER_OF_CARDS; i++) {
                cards.add(cardsToLvl.get(i));
            }
            return create(start + NUMBER_OF_CARDS - 1, cards);
        } else if (length == NUMBER_OF_CARDS - 1 &&
                start == TypeCard.getMinLvl() &&
                levels.get(levels.size() - 1) == TypeCard.getMaxLvl()) {
            LinkedList<Card> cards = new LinkedList<>();
            for (int i = TypeCard.getMinLvl(); i < length; i++) {
                cards.add(cardsToLvl.get(i));
            }
            cards.add(cardsToLvl.get(TypeCard.getMaxLvl()));
            return create(start + NUMBER_OF_CARDS-2, cards);
        } else
            return null;
    }

    @Override
    public void clear() {
        setLevels.clear();
        cardsToLvl.clear();
        count = 0;
    }

    private CardCombination create(int max, List<Card> cards) {
        return new SingleCombination(TypeCombination.STRAIGHT, max, cards);
    }
}
