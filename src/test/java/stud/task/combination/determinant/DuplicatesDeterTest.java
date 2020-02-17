package stud.task.combination.determinant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;
import stud.task.core.component.DeckCards;

import java.util.Arrays;

import static stud.task.card.SuitCard.*;
import static stud.task.card.TypeCard.*;
import static stud.task.combination.domain.TypeCombination.*;

class DuplicatesDeterTest {

    private CombDeter deter;

    @Test
    void expect_FOUR_KIND() {
        DeckCards deckCards = new DeckCards();
        deter = new DuplicatesDeter();
        while (!deckCards.isEmpty()) {
            deter.add(deckCards.poll());
        }
        CardCombination comb = deter.get();
        Assertions.assertTrue(comb != null && comb.getType() == FOUR_KIND);
        Assertions.assertTrue(
                Arrays.asList(new Card[]{
                        new Card(ACE, DIAMONDS),
                        new Card(ACE, CLUBS),
                        new Card(ACE, HEARTS),
                        new Card(ACE, SPADES)
                }
        ).containsAll(comb.getCards()));
    }

    @Test
    void expect_FULL_HOUSE() {
        deter = new DuplicatesDeter();
        Card[] card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(THREE, CLUBS),
                new Card(THREE, HEARTS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        CardCombination comb = deter.get();
        Assertions.assertNotNull(comb);
        Assertions.assertEquals(comb.getType(), FULL_HOUSE);
        Assertions.assertTrue(
                Arrays.asList(new Card[]
                        {
                                new Card(ACE, DIAMONDS),
                                new Card(ACE, CLUBS),
                                new Card(THREE, DIAMONDS),
                                new Card(THREE, CLUBS),
                                new Card(THREE, HEARTS)
                        }
                ).containsAll(comb.getCards())
        );
    }

    @Test
    void expect_TWO_PAIR() {
        deter = new DuplicatesDeter();
        Card[] card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, CLUBS)};
        deter.addAll(Arrays.asList(card));
        CardCombination comb = deter.get();
        Assertions.assertNotNull(comb);
        Assertions.assertSame(comb.getType(), TWO_PAIR);
        Assertions.assertTrue(
                Arrays.asList(new Card[]
                        {
                                new Card(ACE, DIAMONDS),
                                new Card(ACE, CLUBS),
                                new Card(TWO, DIAMONDS),
                                new Card(TWO, CLUBS)
                        }
                ).containsAll(comb.getCards())
        );
    }
}