package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CombDeter  {

    void add(Card card);

    boolean remove(Card card);

    CardCombination get();

    void clear();

    default Optional<CardCombination> getOpt() {
        CardCombination comb = get();
        return comb == null ? Optional.empty() : Optional.of(comb);
    }

    default void addAll(Collection<Card> cards) {
        cards.forEach(this::add);
    }
}
