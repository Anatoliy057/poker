package stud.task.combination.determinant;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface CombDeter {

    void add(Card card);

    List<CardCombination> get();

    default void addAll(Collection<Card> cards) {
        cards.forEach(this::add);
    }

    default List<CardCombination> of(CardCombination comb) {
        LinkedList<CardCombination> list = new LinkedList<>();
        list.add(comb);
        return list;
    }

    default List<CardCombination> empty() {
        return new LinkedList<>();
    }

    default Optional<CardCombination> single(List<CardCombination> list) {
        //TODO exception size in CombDeter::single
        if (list.isEmpty()) return Optional.empty();
        else if (list.size() != 1) {
            System.err.println("TODO");
            return Optional.of(list.get(0));
        }
        else return Optional.of(list.get(0));
    }

}
