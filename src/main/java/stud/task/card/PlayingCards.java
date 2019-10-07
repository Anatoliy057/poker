package stud.task.card;

import java.util.*;

public class PlayingCards implements Iterable<List<Card>> {

    private List<List<Card>> cards;

    public PlayingCards() {
        cards = new LinkedList<>();
        for (int i = 0; i < TypeCard.length; i++) {
            cards.add(new ArrayList<>(SuitCard.length));
        }
    }

    public void add(Card card) {
        cards.get(card.level()).add(card);
    }

    public void addAll(List<Card> cards) {
        for (Card card :
                cards) {
            add(card);
        }
    }

    public LinkedList<Card> sortedListCard() {
        LinkedList<Card> cards = new LinkedList<>();
        for (List<Card> l :
                this.cards) {
            if (l.isEmpty()) continue;
            Collections.sort(l);
            cards.addAll(l);
        }
        return cards;
    }

    public List<Card> listCard() {
        LinkedList<Card> cards = new LinkedList<>();
        for (List<Card> l :
                this.cards) {
            if (l.isEmpty()) continue;
            cards.addAll(l);
        }
        return cards;
    }

    @Override
    public Iterator<List<Card>> iterator() {
        return new Iterator<List<Card>>() {

            ListIterator<List<Card>> it;

            {
                it = cards.listIterator();
                while(it.hasNext())
                    it.next();
            }

            @Override
            public boolean hasNext() {
                return it.hasPrevious();
            }

            @Override
            public List<Card> next() {
                return it.previous();
            }
        };
    }
}
