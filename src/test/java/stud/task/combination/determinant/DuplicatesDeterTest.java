package stud.task.combination.determinant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stud.task.card.Card;
import stud.task.card.DeckCards;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;

import java.util.Arrays;
import java.util.List;

import static stud.task.card.SuitCard.*;
import static stud.task.card.TypeCard.*;

class DuplicatesDeterTest {

    private CombDeter deter;

    @Test
    void except_FOUR_KIND_and_FULL_HOUSE() {
        DeckCards deckCards = new DeckCards();
        deter = new DuplicatesDeter();
        while (!deckCards.isEmpty()) {
            deter.add(deckCards.pullOutCard());
        }
        List<CardCombination> combs = deter.get();
        boolean k, h;
        k = h = false;
        for (CardCombination c :
                combs) {
            if (c.getType() == TypeCombination.FOUR_KIND) k = true;
            if (c.getType() == TypeCombination.FULL_HOUSE) h = true;
        }
        Assertions.assertTrue(k);
        Assertions.assertTrue(h);
    }

    @Test
    void except_PAIR_and_THREE_and_FULL_HOUSE_and_TWO_PAIR() {
        deter = new DuplicatesDeter();
        Card[] card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(THREE, CLUBS),
                new Card(THREE, HEARTS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, CLUBS)};

        deter.addAll(Arrays.asList(card));
        List<CardCombination> combs = deter.get();

        boolean f, t, p, tp;
        f = t = p = tp = false;
        for (CardCombination c :
                combs) {
            switch (c.getType()) {
                case FULL_HOUSE:
                    f = true;
                    break;
                case THREE_KIND:
                    t = true;
                    break;
                case TWO_PAIR:
                    tp = true;
                case PAIR:
                    p = true;
                    break;
            }
        }
        Assertions.assertTrue(f && t && p && tp);
    }

    @Test
    void except_PAIR_and_TWO_PAIR() {
        deter = new DuplicatesDeter();
        Card[] card = new Card[]{new Card(TWO, DIAMONDS),
                new Card(TWO, CLUBS),
                new Card(THREE, DIAMONDS),
                new Card(ACE, DIAMONDS),
                new Card(ACE, CLUBS)};
        deter.addAll(Arrays.asList(card));
        List<CardCombination> combs = deter.get();

        boolean p, tp;
        p = tp = false;
        for (CardCombination c :
                combs) {
            switch (c.getType()) {
                case TWO_PAIR:
                    tp = true;
                case PAIR:
                    p = true;
                    break;
            }
        }
        Assertions.assertTrue(p && tp);
    }
}