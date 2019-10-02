package stud.task.combination.determinant;

import stud.task.combination.domain.CardCombination;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCombDeter implements CombDeter {

    protected List<CardCombination> of(CardCombination comb) {
        LinkedList<CardCombination> list = new LinkedList<>();
        list.add(comb);
        return list;
    }

    protected List<CardCombination> empty() {
        return new LinkedList<>();
    }

    protected Optional<CardCombination> single(List<CardCombination> list) {
        //TODO exception size in CombDeter::single
        if (list.isEmpty()) return Optional.empty();
        else if (list.size() != 1) {
            System.err.println("TODO");
            return Optional.of(list.get(0));
        }
        else return Optional.of(list.get(0));
    }

}
