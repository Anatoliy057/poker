package stud.task.card;

import org.junit.jupiter.api.Test;
import stud.task.core.component.DeckCards;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsSuitToTypeTest {

    private DeckCards deck;
    private CardsSuitToType cards;

    @Test
    void expectSortedCardsForSuit() {
        deck = new DeckCards();
        cards = new CardsSuitToType();

        while (!deck.isEmpty()) {
            cards.add(deck.poll());
        }

        List<Card> sortedCards = cards.listCard();
        Iterator<Card> it = sortedCards.iterator();
        Card c1 = it.next();
        Card c2 = it.next();
        int  b = 0;
        while (it.hasNext()) {
            System.out.println(c1 + " " + c2);
            if (c1.compareTo(c2) < 0) {
                b++;
            }
            System.out.println(c1.compareTo(c2));
            c1 = c2;
            c2 = it.next();
        }
        assertTrue(b < SuitCard.length);
    }
}