package stud.task.card;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayingCardsTest {

    private DeckCards deck;
    private PlayingCards cards;

    @Test
    void expectSortedCards() {
        deck = new DeckCards();
        cards = new PlayingCards();

        while(!deck.isEmpty()) {
            cards.add(deck.pullOutCard());
        }

        List<Card> sortedCards = cards.sortedListCard();
        Iterator<Card> it = sortedCards.iterator();
        Card c1 = it.next();
        Card c2 = it.next();
        boolean b = true;
        while (it.hasNext()) {
            if (c1.compareTo(c2) > 0) {
                b = false;
            }
            c1 = c2;
            c2 = it.next();
        }
        assertTrue(b);
    }

    @Test
    void expectAlmostSortedCards() {
        deck = new DeckCards();
        cards = new PlayingCards();

        while(!deck.isEmpty()) {
            cards.add(deck.pullOutCard());
        }

        List<Card> sortedCards = cards.listCard();
        Iterator<Card> it = sortedCards.iterator();
        Card c1 = it.next();
        Card c2 = it.next();
        boolean b = true;
        while (it.hasNext()) {
            if (c1.level() - c2.level() > 0) {
                b = false;
            }
            c1 = c2;
            c2 = it.next();
        }
        assertTrue(b);
    }
}