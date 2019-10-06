package stud.task.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DeckCards {

    private Queue<Card> deck;

    public DeckCards() {
        LinkedList<Card> deck = new LinkedList<>();
        for (TypeCard l:
             TypeCard.values()) {
            for (SuitCard s :
                    SuitCard.values()) {
                deck.add(new Card(l, s));
            }
        }
        Collections.shuffle(deck);
        this.deck = deck;
    }

    public Card pullOutCard() {
        return deck.poll();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

}
