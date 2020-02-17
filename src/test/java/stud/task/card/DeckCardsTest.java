package stud.task.card;

import org.junit.jupiter.api.Test;
import stud.task.core.component.DeckCards;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckCardsTest {

    @Test
    void expectDifferentDecks() {
        DeckCards deck = new DeckCards();
        List<Card> list1 = new LinkedList<>();
        List<Card> list2 = new LinkedList<>();

        while (!deck.isEmpty()) {
            list1.add(deck.poll());
        }

        deck.reset();
        while (!deck.isEmpty()) {
            list2.add(deck.poll());
        }

        //full decks
        assertEquals(list1.size(), TypeCard.length * SuitCard.length);
        assertEquals(list2.size(), TypeCard.length * SuitCard.length);

        assertNotEquals(list1, list2);
    }
}