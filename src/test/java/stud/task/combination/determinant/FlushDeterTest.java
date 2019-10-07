package stud.task.combination.determinant;

import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.FlushComb;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static stud.task.card.SuitCard.*;
import static stud.task.card.TypeCard.*;
import static stud.task.card.TypeCard.ACE;

class FlushDeterTest {

    private CombDeter deter = new FlushDeter();
    private List<CardCombination> list;
    private FlushComb flush;
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
        list = deter.get();
        flush = (FlushComb) list.get(0);

        assertEquals(flush.getPriority(), 0);
        assertEquals(flush.getMax(), ACE.getLvl());
        assertEquals(flush.getMin(), TWO.getLvl());
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
                new Card(JACK, DIAMONDS),
                new Card(FIVE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        list = deter.get();
        flush = (FlushComb) list.get(0);

        assertEquals(flush.getPriority(), DIAMONDS.getPriority());
        assertEquals(flush.getMax(), KING.getLvl());
        assertEquals(flush.getMin(), FIVE.getLvl());
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
        assertTrue(deter.get().isEmpty());
    }
}