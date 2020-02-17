package stud.task.card;

import org.junit.jupiter.api.Test;
import stud.task.core.component.DeckCards;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardsTypeToSuitTest {

    private DeckCards deck;
    private CardsTypeToSuit cards;

    @Test
    void expectSortedCards() {
        deck = new DeckCards();
        cards = new CardsTypeToSuit();

        while(!deck.isEmpty()) {
            cards.add(deck.poll());
        }

        List<Card> sortedCards = cards.listCard();
        Iterator<Card> it = sortedCards.iterator();
        Card c1 = it.next();
        Card c2 = it.next();
        boolean b = true;
        while (it.hasNext()) {
            System.out.println(c1 + " " + c2);
            if (c1.compareTo(c2) < 0) {
                b = false;
            }
            System.out.println(c1.compareTo(c2));
            c1 = c2;
            c2 = it.next();
        }
        assertTrue(b);
    }
}