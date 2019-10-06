package stud.task.card;

import org.junit.jupiter.api.Test;
import static stud.task.card.SuitCard.*;
import static stud.task.card.TypeCard.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    private Card card1;
    private Card card2;

    @Test
    void compareTo() {
        //card1 > card2
        card1 = new Card(ACE, DIAMONDS);
        card2 = new Card(TWO, DIAMONDS);

        assertTrue(card1.compareTo(card2) > 0);

        //card1 == card2
        card1 = new Card(TEN, DIAMONDS);
        card2 = new Card(TEN, DIAMONDS);

        assertEquals(0, card1.compareTo(card2));

        //card1 > card2
        card1 = new Card(TEN, DIAMONDS);
        card2 = new Card(TEN, HEARTS);

        assertTrue(card1.compareTo(card2) > 0);
    }

    @Test
    void equals() {
        //card1 != card2
        card1 = new Card(ACE, DIAMONDS);
        card2 = new Card(TWO, DIAMONDS);

        assertNotEquals(card1, card2);

        //card1 == card2
        card1 = new Card(TEN, DIAMONDS);
        card2 = new Card(TEN, DIAMONDS);

        assertEquals(card1, card2);

        //card1 != card2
        card1 = new Card(TEN, DIAMONDS);
        card2 = new Card(TEN, HEARTS);

        assertNotEquals(card1, card2);
    }
}