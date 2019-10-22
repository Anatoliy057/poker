package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.PlayingCards;
import stud.task.combination.domain.*;

import java.util.*;

import static stud.task.combination.domain.TypeCombination.*;

public class DuplicatesDeter extends AbstractCombDeter {

    private PlayingCards cards;

    private Queue<List<Card>> pairs = new PriorityQueue<>(
            Comparator.comparingInt(l -> -l.get(0).level()));
    private Queue<List<Card>> triples = new PriorityQueue<>(
            Comparator.comparingInt(l -> -l.get(0).level()));

    public DuplicatesDeter() {
        cards = new PlayingCards();
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public CardCombination get() {
        Queue<CardCombination> combs = new PriorityQueue<>((c1, c2) -> -c1.compareTo(c2));
        for (List<Card> list :
                cards) {
            int level;
            TypeCombination type;
            switch (list.size()) {
                case 2:
                    type = PAIR;
                    level = list.get(0).level();
                    pairs.add(list);
                    break;
                case 3:
                    type = THREE_KIND;
                    level = list.get(0).level();
                    triples.add(list);
                    break;
                case 4:
                    type = FOUR_KIND;
                    level = list.get(0).level();
                    break;
                default:
                    continue;
            }
            combs.add(createSingle(type, level, list));
            if (pairs.size() > 0 && triples.size() > 0) {
                List<Card> pair = pairs.poll();
                List<Card> triple = triples.poll();
                combs.add(createDouble(
                        FULL_HOUSE,
                        triple.get(0).level(),
                        pair.get(0).level(),
                        concat(triple, pair)
                ));
            }
            if (triples.size() > 0) {
                List<Card> triple = triples.poll();
                combs.add(createSingle(
                        THREE_KIND,
                        triple.get(0).level(),
                        triple
                        ));
            }
            if (pairs.size() > 1) {
                List<Card> pairHigh = pairs.poll();
                List<Card> pairSmall = pairs.poll();
                combs.add(createDouble(
                        TWO_PAIR,
                        pairHigh.get(0).level(),
                        pairSmall.get(0).level(),
                        concat(pairHigh, pairSmall)
                ));
            }
        }
        return combs.poll();
    }

    @Override
    public void clear() {
        triples.clear();
        pairs.clear();
        cards.clear();
    }

    private SingleCombination createSingle(TypeCombination type, int lvl, List<Card> cards) {
        return new SingleCombination(type, lvl, cards);
    }

    private DoubleCombination createDouble(TypeCombination type, int lvl1, int lvl2, List<Card> cards) {
        return new DoubleCombination(type, lvl1, lvl2, cards);
    }

    private List<Card> concat(List<Card> l1, List<Card> l2) {
        LinkedList<Card> concat = new LinkedList<>(l1);
        concat.addAll(l2);
        return concat;
    }
}
