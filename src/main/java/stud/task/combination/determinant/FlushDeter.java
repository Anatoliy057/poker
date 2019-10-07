package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.FlushComb;

import java.util.*;

public class FlushDeter extends AbstractCombDeter {

    public final static int NUMBER_OF_CARDS = 5;

    private int max = -1;
    private int min = Integer.MAX_VALUE;
    private List<List<Card>> cards;
    private int count = 0;

    public FlushDeter() {
        cards = new ArrayList<>(SuitCard.length);
        for (int i = 0; i < SuitCard.length; i++) {
            cards.add(new LinkedList<>());
        }
    }

    @Override
    public void add(Card card) {
        cards.get(card.priority()).add(card);
        count++;
    }

    @Override
    public List<CardCombination> get() {
        if (count < NUMBER_OF_CARDS) return empty();
        Collections.reverse(cards);
        for (List<Card> l : cards) {
            if (l.size() >= NUMBER_OF_CARDS) {
                return of(create(l));
            }
        }
        return empty();
    }
    
    private CardCombination create(List<Card> cards) {
        HashSet<Integer> set = new HashSet<>();
        cards.forEach(c -> set.add(c.level()));
        return new FlushComb(set, cards.get(0).priority());
    }
}
