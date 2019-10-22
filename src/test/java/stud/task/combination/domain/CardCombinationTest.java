package stud.task.combination.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import stud.task.card.Card;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static stud.task.combination.domain.TypeCombination.*;
import static stud.task.card.TypeCard.*;
import static stud.task.card.SuitCard.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CardCombinationTest {

    private CardCombination comb1;
    private CardCombination comb2;

    private List<Card> empty = new LinkedList<>();

    @Test
    void testMethod_compareTo_inDiffImplements() {
        //------------- common cases -------------
        //FULL_HOUSE > HIGH_CARD
        comb1 = new DoubleCombination(FULL_HOUSE, 10, 3, empty);
        comb2 = new SingleCombination(HIGH_CARD, 10, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //STRAIGHT > HIGH_CARD
        comb1 = new SingleCombination(STRAIGHT, ACE, empty);
        comb2 = new SingleCombination(HIGH_CARD, JACK, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE > STRAIGHT
        comb1 = new DoubleCombination(FULL_HOUSE, 1, 2, empty);
        comb2 = new SingleCombination(STRAIGHT, ACE, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //HIGH_CARD == HIGH_CARD
        comb1 = new SingleCombination(HIGH_CARD, JACK, empty);
        comb2 = new SingleCombination(HIGH_CARD, JACK, empty);

        Assertions.assertEquals(0, comb1.compareTo(comb2));

        //------------- special cases -------------
        //TypicalComb_9 > TypicalComb_8
        comb1 = new SingleCombination(UNKNOWN, 9, empty);
        comb2 = new SingleCombination(UNKNOWN, 8, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FLUSH_FIVE_ACE > FLUSH_TWO_ACE
        comb1 = new DoubleCombination(FLUSH, FIVE, ACE, empty);
        comb2 = new DoubleCombination(FLUSH, TWO, ACE, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FLUSH_FIVE_ACE > FLUSH_FIVE_TEN
        comb1 = new DoubleCombination(FLUSH, FIVE, ACE, empty);
        comb2 = new DoubleCombination(FLUSH, FIVE, TEN, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE_FIVE_ACE > FULL_HOUSE_TWO_ACE
        comb1 = new DoubleCombination(FULL_HOUSE, FIVE, ACE, empty);
        comb2 = new DoubleCombination(FULL_HOUSE, TWO, ACE, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE_FIVE_ACE > FULL_HOUSE_FIVE_TEN
        comb1 = new DoubleCombination(FULL_HOUSE, FIVE, ACE, empty);
        comb2 = new DoubleCombination(FULL_HOUSE, FIVE, TEN, empty);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE_TWO_ACE == FULL_HOUSE_TWO_ACE
        comb1 = new DoubleCombination(FULL_HOUSE, TWO, ACE, empty);
        comb2 = new DoubleCombination(FULL_HOUSE, TWO, ACE, empty);

        assertEquals(0, comb1.compareTo(comb2));
    }
}