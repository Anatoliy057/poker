package stud.task.combination.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static stud.task.combination.domain.TypeCombination.*;
import static stud.task.card.TypeCard.*;
import static stud.task.card.SuitCard.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


class CardCombinationTest {

    private CardCombination comb1;
    private CardCombination comb2;

    @Test
    void testMethod_compareTo_inDiffImplements() {
        //------------- common cases -------------
        //FLUSH > HIGH_CARD
        comb1 = new FlushComb(1, 10, 3);
        comb2 = new TypicalComb(HIGH_CARD, 10);

        assertTrue(comb1.compareTo(comb2) > 0);

        //STRAIGHT > HIGH_CARD
        comb1 = new TypicalComb(STRAIGHT, ACE);
        comb2 = new TypicalComb(HIGH_CARD, JACK);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE > STRAIGHT
        comb1 = new FullHouseComb(1, 2);
        comb2 = new TypicalComb(STRAIGHT, ACE);

        assertTrue(comb1.compareTo(comb2) > 0);

        //HIGH_CARD == HIGH_CARD
        comb1 = new TypicalComb(HIGH_CARD, JACK);
        comb2 = new TypicalComb(HIGH_CARD, JACK);

        Assertions.assertEquals(0, comb1.compareTo(comb2));

        //------------- special cases -------------
        //TypicalComb_9 > TypicalComb_8
        comb1 = new TypicalComb(UNKNOWN, 9);
        comb2 = new TypicalComb(UNKNOWN, 8);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FLUSH_FIVE_ACE > FLUSH_TWO_ACE
        comb1 = new FlushComb(FIVE, ACE, DIAMONDS);
        comb2 = new FlushComb(TWO, ACE, DIAMONDS);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FLUSH_FIVE_ACE > FLUSH_FIVE_TEN
        comb1 = new FlushComb(FIVE, ACE, DIAMONDS);
        comb2 = new FlushComb(FIVE, TEN, DIAMONDS);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FLUSH_TWO_ACE_D == FLUSH_TWO_ACE_C
        comb1 = new FlushComb(TWO, ACE, DIAMONDS);
        comb2 = new FlushComb(TWO, ACE, CLUBS);

        assertEquals(0, comb1.compareTo(comb2));

        //FULL_HOUSE_FIVE_ACE > FULL_HOUSE_TWO_ACE
        comb1 = new FullHouseComb(FIVE, ACE);
        comb2 = new FullHouseComb(TWO, ACE);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE_FIVE_ACE > FULL_HOUSE_FIVE_TEN
        comb1 = new FullHouseComb(FIVE, ACE);
        comb2 = new FullHouseComb(FIVE, TEN);

        assertTrue(comb1.compareTo(comb2) > 0);

        //FULL_HOUSE_TWO_ACE == FULL_HOUSE_TWO_ACE
        comb1 = new FullHouseComb(TWO, ACE);
        comb2 = new FullHouseComb(TWO, ACE);

        assertEquals(0, comb1.compareTo(comb2));
    }
}