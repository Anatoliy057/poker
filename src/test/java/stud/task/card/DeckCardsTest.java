package stud.task.card;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckCardsTest {

    @Test
    void exceptDifferentDecks() {
        DeckCards deck = new DeckCards();
        List<Card> list1 = new LinkedList<>();
        List<Card> list2 = new LinkedList<>();

        while (!deck.isEmpty()) {
            list1.add(deck.pullOutCard());
        }

        deck = new DeckCards();
        while (!deck.isEmpty()) {
            list2.add(deck.pullOutCard());
        }

        //full decks
        assertEquals(list1.size(), TypeCard.length * SuitCard.length);
        assertEquals(list2.size(), TypeCard.length * SuitCard.length);

        System.out.println(list1);
        System.out.println(list2);

        assertNotEquals(list1, list2);
    }
}