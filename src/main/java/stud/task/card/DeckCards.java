package stud.task.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DeckCards {

    private LinkedList<Card> deck;

    public DeckCards() {
        deck = new LinkedList<>();
        load();
    }

    public void reset() {
        deck.clear();
        load();
    }

    private void clear() {
        deck.clear();
    }

    private void load() {
        for (TypeCard l:
                TypeCard.values()) {
            for (SuitCard s :
                    SuitCard.values()) {
                deck.add(new Card(l, s));
            }
        }
        Collections.shuffle(deck);
    }

    public Card pullOutCard() {
        return deck.poll();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

}
