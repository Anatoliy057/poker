package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.CardsTypeToSuit;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.SingleCombination;
import stud.task.combination.domain.TypeCombination;

import java.util.*;

public class PairDeter extends AbstractCombDeter {

    CardsTypeToSuit cards = new CardsTypeToSuit();

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public CardCombination get() {
        LinkedList<List<Card>> pairs = new LinkedList<>();
        Queue<List<Card>> triples = new LinkedList<>();

        for (List<Card> list :
                cards) {
            switch (list.size()) {
                case 2: {
                    pairs.add(list);
                    break;
                }
                case 3: {
                    triples.add(list);
                    break;
                }
            }
        }
        int tail = 0;
        if (pairs.size() / 2 > 1) {
            tail = -1;
            if (triples.size() > 0) {
                tail = 0;
            }
        } else if (pairs.size() == 1) {
            if (!(triples.size() > 0))
                tail = 1;
        }
        List<Card> cards;
        if (tail > 0) {
            pairs.sort(Comparator.comparingInt(l -> -l.get(0).level()));
            cards = pairs.getLast();
            return new SingleCombination(TypeCombination.PAIR, cards.get(0).level(), cards);
        } else if (tail < 0) {
            pairs.sort(Comparator.comparingInt(l -> -l.get(0).level()));
            cards = pairs.getFirst();
            return new SingleCombination(TypeCombination.PAIR, cards.get(0).level(), cards);
        } else {
            return null;
        }
    }

    @Override
    public void clear() {
        cards.clear();
    }
}
