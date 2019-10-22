package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCombDeter implements CombDeter {

    @Override
    public Optional<CardCombination> getOpt() {
        CardCombination comb = get();
        return comb == null ? Optional.empty() : Optional.of(comb);
    }
}
