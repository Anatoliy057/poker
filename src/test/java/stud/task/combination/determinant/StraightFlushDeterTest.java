package stud.task.combination.determinant;

import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.FlushComb;
import stud.task.combination.domain.SingleCombination;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static stud.task.card.SuitCard.*;
import static stud.task.card.SuitCard.CLUBS;
import static stud.task.card.TypeCard.*;
import static stud.task.combination.domain.TypeCombination.*;

class StraightFlushDeterTest {

    private CombDeter deter;
    private List<CardCombination> list;
    private SingleCombination straight;
    private FlushComb flush;
    private Card[] card;

    @Test
    void exceptStraightFlushFromAceToFive() {
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
        list = deter.get();
        straight = (SingleCombination) list.get(0);

        assertEquals(straight.getPriority(), FIVE.getLvl());
        assertEquals(straight.getType(), STRAIGHT_FLUSH);
    }

    @Test
    void exceptFlushFromTwoToAce() {
        deter = new StraightFlushDeter();
        card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(FOUR, CLUBS),
                new Card(FIVE, HEARTS),
                new Card(SIX, DIAMONDS),
                new Card(ACE, DIAMONDS),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(EIGHT, CLUBS),
                new Card(FOUR, DIAMONDS)};

        deter.addAll(Arrays.asList(card));
        list = deter.get();
        flush = (FlushComb) list.get(0);

        assertEquals(flush.getMax(), ACE.getLvl());
        assertEquals(flush.getMin(), TWO.getLvl());
        assertEquals(flush.getType(), FLUSH);
    }

    @Test
    void exceptRoyalFlushClubs() {
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
        list = deter.get();
        straight = (SingleCombination) list.get(0);

        assertEquals(straight.getPriority(), CLUBS.getPriority());
        assertEquals(straight.getType(), ROYAL_FLUSH);
    }

    @Test
    void exceptEmpty() {
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
        list = deter.get();

        assertTrue(list.isEmpty());
    }
}