package stud.task.card;

import java.util.*;

public class CardsTypeToSuit implements Iterable<List<Card>> {

    private Map<TypeCard, Map<SuitCard, Card>> cards;

    public CardsTypeToSuit() {
        cards = new TreeMap<>((t1, t2) -> -t1.compareLvlTo(t2));
    }

    public void add(Card card) {
        Map<SuitCard, Card> map;
        if ((map = cards.get(card.getType())) == null) {
            map = new TreeMap<>((s1, s2) -> -s1.comparePriorTo(s2));
            map.put(card.getSuit(), card);
            cards.put(card.getType(), map);
        } else {
            map.put(card.getSuit(), card);
        }
    }

    public void addAll(List<Card> cards) {
        for (Card card :
                cards) {
            add(card);
        }
    }

    public List<Card> listCard() {
        LinkedList<Card> cards = new LinkedList<>();
        for (Map<SuitCard, Card> m :
                this.cards.values()) {
            cards.addAll(m.values());
        }
        return cards;
    }

    @Override
    public Iterator<List<Card>> iterator() {
        return new Iterator<List<Card>>() {

            Iterator<Map<SuitCard, Card>> it = cards.values().iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public List<Card> next() {
                return new LinkedList<>(it.next().values());
            }
        };
    }

    public void clear() {
        cards.clear();
    }
}
