package stud.task.combination.determinant;

import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.DoubleCombination;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static stud.task.card.SuitCard.*;
import static stud.task.card.TypeCard.*;
import static stud.task.card.TypeCard.ACE;

class FlushDeterTest {

    private CombDeter deter = new FlushDeter();
    private DoubleCombination flush;
    private Card[] card;

    @Test
    void expectFlushClubsFromTwoToAce() {
        deter = new FlushDeter();
        card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(THREE, CLUBS),
                new Card(THREE, HEARTS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(ACE, CLUBS),
                new Card(TEN, CLUBS),
                new Card(FIVE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        flush = (DoubleCombination) deter.get();

        assertNotNull(flush);
        assertEquals(flush.getLvl1(), ACE.getLvl());
        assertEquals(flush.getLvl2(), TWO.getLvl());
        assertEquals(flush.getCards().size(), FlushDeter.NUMBER_OF_CARDS);
    }

    @Test
    void expectFlushDiamondsFromFiveToKing() {
        deter = new FlushDeter();
        card = new Card[]{new Card(FIVE, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(TEN, DIAMONDS),
                new Card(THREE, CLUBS),
                new Card(TEN, CLUBS),
                new Card(KING, DIAMONDS),
                new Card(SIX, DIAMONDS),
                new Card(JACK, DIAMONDS),
                new Card(ACE, CLUBS),
                new Card(FIVE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        flush = (DoubleCombination) deter.get();
        assertNotNull(flush);

        assertEquals(flush.getLvl1(), KING.getLvl());
        assertEquals(flush.getLvl2(), FIVE.getLvl());
        assertEquals(flush.getCards().size(), FlushDeter.NUMBER_OF_CARDS);
        assertEquals(flush.getCards().size(), StraightDeter.NUMBER_OF_CARDS);
        assertTrue(
                Arrays.asList(new Card[]
                        {
                                new Card(FIVE, DIAMONDS),
                                new Card(TEN, DIAMONDS),
                                new Card(JACK, DIAMONDS),
                                new Card(SIX, DIAMONDS),
                                new Card(KING, DIAMONDS)
                        }
                ).containsAll(flush.getCards())
        );
    }

    @Test
    void expectEmpty() {
        deter = new FlushDeter();
        card = new Card[]{new Card(FIVE, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, CLUBS),
                new Card(SIX, DIAMONDS),
                new Card(JACK, DIAMONDS),
                new Card(ACE, CLUBS),
                new Card(JACK, DIAMONDS),
                new Card(FIVE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        assertNull(deter.get());
    }
}