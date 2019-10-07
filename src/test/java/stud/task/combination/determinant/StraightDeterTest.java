package stud.task.combination.determinant;

import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.SingleCombination;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static stud.task.card.SuitCard.*;
import static stud.task.card.SuitCard.CLUBS;
import static stud.task.card.TypeCard.*;
import static stud.task.card.TypeCard.TWO;

class StraightDeterTest {

    private CombDeter deter;
    private List<CardCombination> list;
    private SingleCombination straight;
    private Card[] card;

    @Test
    void expectStraightFromAceToFive() {
        deter = new StraightDeter();
        card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(FOUR, CLUBS),
                new Card(FIVE, HEARTS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(TEN, CLUBS),
                new Card(TEN, CLUBS)};

        deter.addAll(Arrays.asList(card));
        list = deter.get();
        straight = (SingleCombination) list.get(0);

        assertEquals(straight.getPriority(), FIVE.getLvl());
    }

    @Test
    void expectEmpty1() {
        deter = new StraightDeter();
        card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(FOUR, CLUBS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(TEN, CLUBS),
                new Card(TEN, CLUBS)};

        deter.addAll(Arrays.asList(card));
        list = deter.get();

        assertTrue(list.isEmpty());
    }

    @Test
    void expectStraightFromTenToAce() {
        deter = new StraightDeter();
        card = new Card[]{new Card(QUEEN, DIAMONDS),
                new Card(SEVEN, CLUBS),
                new Card(JACK, DIAMONDS),
                new Card(FOUR, CLUBS),
                new Card(FOUR, HEARTS),
                new Card(EIGHT, DIAMONDS),
                new Card(ACE, SPADES),
                new Card(JACK, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(TEN, CLUBS),
                new Card(KING, CLUBS)};

        deter.addAll(Arrays.asList(card));
        list = deter.get();
        straight = (SingleCombination) list.get(0);

        assertEquals(straight.getPriority(), ACE.getLvl());
    }

    @Test
    void expectEmpty2() {
        deter = new StraightDeter();
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