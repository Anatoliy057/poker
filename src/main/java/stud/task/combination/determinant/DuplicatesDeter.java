package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.card.PlayingCards;
import stud.task.combination.domain.*;

import java.util.LinkedList;
import java.util.List;

import static stud.task.combination.domain.TypeCombination.*;

public class DuplicatesDeter extends AbstractCombDeter {

    private PlayingCards cards;

    private class FullHouse {
        List<Card> list1;
        List<Card> list2;

        void add(List<Card> list) {
            switch (list.size()) {
                case 4:
                    if (list2 == null || this.list2.get(0).level() < list2.get(0).level()) {
                        list2 = list;
                        break;
                    }
                case 3:
                    if (list2 == null || this.list2.get(0).level() < list2.get(0).level()) {
                        list2 = list;
                        break;
                    }
                case 2:
                    if (list1 == null || this.list1.get(0).level() < list1.get(0).level()) {
                        list1 = list;
                        break;
                    }
                    break;
            }
        }
        boolean isEmpty() {
            return list1 == null || list2 == null || list1.isEmpty() || list2.isEmpty();
        }

        int lvl1() {
            return list2.get(0).level();
        }

        int lvl2() {
            return list1.get(0).level();
        }

        void clear() {
            list1 = list2 = null;
        }
    }

    private class TwoPair extends FullHouse {

        void add(List<Card> list) {
            if (list1 == null || list.get(0).level() > list1.get(0).level()) {
                list2 = list1;
                list1 = list;
            } else if (list2 == null || list.get(0).level() > list2.get(0).level()) {
                list2 = list;
            }
        }
    }

    private FullHouse fullHouse = new FullHouse();
    private TwoPair twoPair = new TwoPair();

    public DuplicatesDeter() {
        cards = new PlayingCards();
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public List<CardCombination> get() {
        List<CardCombination> combs = new LinkedList<>();
        for (List<Card> list :
                cards) {
            int level;
            TypeCombination type;
            List<Card> cards;
            switch (list.size()) {
                case 0:
                    continue;
                case 1:
                    type = HIGH_CARD;
                    level = list.get(0).level();
                    break;
                case 2:
                    type = PAIR;
                    level = list.get(0).level();
                    fullHouse.add(list);
                    twoPair.add(list);
                    break;
                case 3:
                    type = THREE_KIND;
                    level = list.get(0).level();
                    fullHouse.add(list);
                    twoPair.add(list);
                    break;
                case 4:
                    type = FOUR_KIND;
                    level = list.get(0).level();
                    fullHouse.add(list);
                    break;
                default:
                    type = UNKNOWN;
                    level = -1;
            }
            combs.add(create(type, level, list));
            if (!fullHouse.isEmpty()) {
                AbstractCardComb aComb = new DoubleCombination(FULL_HOUSE, fullHouse.lvl1(), fullHouse.lvl2());
                aComb.addAllCards(fullHouse.list1.subList(0, 3));
                aComb.addAllCards(fullHouse.list2.subList(0, 2));
                combs.add(aComb);
                fullHouse.clear();
            }
            if (!twoPair.isEmpty()) {
                AbstractCardComb aComb = new DoubleCombination(TWO_PAIR, twoPair.lvl1(), twoPair.lvl2());
                aComb.addAllCards(twoPair.list1.subList(0, 2));
                aComb.addAllCards(twoPair.list2.subList(0, 2));
                combs.add(aComb);
                twoPair.clear();
            }
        }
        return combs;
    }

    private SingleCombination create(TypeCombination type, int lvl) {
        return new SingleCombination(type, lvl);
    }

    private SingleCombination create(TypeCombination type, int lvl, List<Card> cards) {
        return new SingleCombination(type, lvl, cards);
    }
}
