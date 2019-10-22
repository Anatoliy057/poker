package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CombDeter {

    void add(Card card);

    CardCombination get();

    void clear();

    default Optional<CardCombination> getOpt() {
        return Optional.of(get());
    }

    default void addAll(Collection<Card> cards) {
        cards.forEach(this::add);
    }
}
