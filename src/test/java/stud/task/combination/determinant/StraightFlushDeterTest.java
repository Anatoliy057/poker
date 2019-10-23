package stud.task.combination.determinant;

import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.DoubleCombination;
import stud.task.combination.domain.SingleCombination;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static stud.task.card.SuitCard.*;
import static stud.task.card.SuitCard.CLUBS;
import static stud.task.card.TypeCard.*;
import static stud.task.combination.domain.TypeCombination.*;

class StraightFlushDeterTest {

    private CombDeter deter;
    private SingleCombination straight;
    private Card[] card;

    @Test
    void expectStraightFlushFromAceToFive() {
        deter = new StraightFlushDeter();
        card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(FOUR, DIAMONDS),
                new Card(FIVE, DIAMONDS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(TEN, CLUBS),
                new Card(FOUR, CLUBS)};

        deter.addAll(Arrays.asList(card));
        straight = (SingleCombination) deter.get();

        assertNotNull(straight);
        assertEquals(straight.getPriority(), FIVE.getLvl());
        assertEquals(straight.getType(), STRAIGHT_FLUSH);
        assertEquals(straight.getCards().size(), StraightDeter.NUMBER_OF_CARDS);
        assertTrue(
                Arrays.asList(new Card[]
                        {
                                new Card(FIVE, DIAMONDS),
                                new Card(ACE, DIAMONDS),
                                new Card(THREE, DIAMONDS),
                                new Card(FOUR, DIAMONDS),
                                new Card(TWO, DIAMONDS)
                        }
                ).containsAll(straight.getCards())
        );
    }

    @Test
    void expectRoyalFlushClubs() {
        deter = new StraightFlushDeter();
        card = new Card[]{new Card(QUEEN, CLUBS),
                new Card(SEVEN, DIAMONDS),
                new Card(JACK, CLUBS),
                new Card(FOUR, HEARTS),
                new Card(FOUR, HEARTS),
                new Card(NINE, DIAMONDS),
                new Card(ACE, CLUBS),
                new Card(JACK, DIAMONDS),
                new Card(TWO, HEARTS),
                new Card(TEN, CLUBS),
                new Card(KING, CLUBS)};

        deter.addAll(Arrays.asList(card));
        straight = (SingleCombination) deter.get();

        assertNotNull(straight);
        assertEquals(straight.getPriority(), CLUBS.getPriority());
        assertEquals(straight.getType(), ROYAL_FLUSH);
    }

    @Test
    void expectEmpty() {
        deter = new StraightFlushDeter();
        card = new Card[]{new Card(QUEEN, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(JACK, DIAMONDS),
                new Card(FOUR, CLUBS),
                new Card(FOUR, HEARTS),
                new Card(NINE, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(KING, CLUBS)};

        deter.addAll(Arrays.asList(card));
        assertNull(deter.get());
    }
}