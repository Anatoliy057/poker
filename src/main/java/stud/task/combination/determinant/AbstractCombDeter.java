package stud.task.combination.determinant;

import stud.task.combination.domain.CardCombination;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractCombDeter implements CombDeter {

    protected List<CardCombination> of(CardCombination comb) {
        LinkedList<CardCombination> list = new LinkedList<>();
        list.add(comb);
        return list;
    }

    protected List<CardCombination> empty() {
        return new LinkedList<>();
    }
}
