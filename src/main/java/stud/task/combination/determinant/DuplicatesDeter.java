package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.PlayingCards;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.FullHouseComb;
import stud.task.combination.domain.TypeCombination;
import stud.task.combination.domain.TypicalComb;

import java.util.LinkedList;
import java.util.List;

public class DuplicatesDeter implements CombDeter {

    private List<CardCombination> combs;
    private PlayingCards cards;


    private class FullHouse {
        private List<Card> pair;
        private List<Card> three;

        private void add(List<Card> list) {
            switch (list.size()) {
                case 2:
                    if (pair == null || this.pair.get(0).level() < pair.get(0).level()) {
                        pair = list;
                    }
                    break;
                case 3:
                    if (three == null || this.three.get(0).level() < three.get(0).level()) {
                        three = list;
                    }
                    break;
            }
        }
        private boolean isEmpty() {
            return pair.isEmpty() || three.isEmpty();
        }

        private int lvl1() {
            return three.get(0).level();
        }

        private int lvl2() {
            return pair.get(0).level();
        }
    }

    FullHouse fh = new FullHouse();

    public DuplicatesDeter() {
        combs = new LinkedList<>();
        cards = new PlayingCards();
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public List<CardCombination> get() {
        for (List<Card> list :
                cards) {
            int level;
            TypeCombination type;
            switch (list.size()) {
                case 0:
                    continue;
                case 1:
                    type = TypeCombination.HIGH_CARD;
                    level = list.get(0).level();
                    break;
                case 2:
                    type = TypeCombination.PAIR;
                    level = list.get(0).level();
                    fh.add(list);
                    break;
                case 3:
                    type = TypeCombination.THREE_KIND;
                    level = list.get(0).level();
                    fh.add(list);
                    break;
                case 4:
                    type = TypeCombination.FOUR_KIND;
                    level = list.get(0).level();
                    break;
                default:
                    type = TypeCombination.UNKNOWN;
                    level = -1;
            }
            combs.add(create(type, level));
            if (!fh.isEmpty()) {
                combs.add(new FullHouseComb(fh.lvl1(), fh.lvl2()));
            }
            return empty();
        }
        return combs;
    }

    private TypicalComb create(TypeCombination type, int lvl) {
        return new TypicalComb(type, lvl);
    }
}
