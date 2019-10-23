package stud.task.card;

import java.util.*;

public class CardsSuitToType implements Iterable<List<Card>> {
    private Map<SuitCard, Map<TypeCard, Card>> cards;

    public CardsSuitToType() {
        cards = new TreeMap<>((t1, t2) -> -t1.comparePriorTo(t2));
    }

    public void add(Card card) {
        Map<TypeCard, Card> map;
        if ((map = cards.get(card.getSuit())) == null) {
            map = new TreeMap<>((s1, s2) -> -s1.compareLvlTo(s2));
            map.put(card.getType(), card);
            cards.put(card.getSuit(), map);
        } else {
            map.put(card.getType(), card);
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
        for (Map<TypeCard, Card> m :
                this.cards.values()) {
            cards.addAll(m.values());
        }
        return cards;
    }

    @Override
    public Iterator<List<Card>> iterator() {
        return new Iterator<List<Card>>() {

            Iterator<Map<TypeCard, Card>> it = cards.values().iterator();

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
