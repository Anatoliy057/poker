package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.Collection;
import java.util.List;

public interface CombDeter {

    void add(Card card);

    List<CardCombination> get();

    default void addAll(Collection<Card> cards) {
        cards.forEach(this::add);
    }
}
