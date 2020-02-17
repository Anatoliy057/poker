package stud.task.combination;

import stud.task.card.Card;
import stud.task.combination.determinant.CombDeter;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.util.FactoryObjects;
import stud.task.util.Reference;

import java.util.*;
import java.util.stream.Collectors;

public class ListComb {

    private List<CombDeter> deters;
    private Map<TypeCombination, Reference<CardCombination>> subst;

    public ListComb(List<CombDeter> deters) {
        this.deters = deters;
        FrameworkCombs subst = FrameworkCombs.getInstance();
        this.subst = subst.get();
    }

    public void add(Card card) {
        deters.forEach(d -> d.add(card));
    }

    public void addAll(Collection<Card> cards) {
        deters.forEach(d -> d.addAll(cards));
    }

    public boolean remove(Card card) {
        boolean res = true;
        for (CombDeter cd :
                deters) {
            res &= cd.remove(card);
        }
        return res;
    }

    public boolean removeAll(Collection<Card> cards) {
        boolean res = true;
        for (Card c :
                cards) {
            res &= remove(c);
        }
        return res;
    }

    public List<CardCombination> getAllCombs() {
        return deters.stream()
                .map(CombDeter::getOpt)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<CardCombination> getFilterCombs() {
        deters.stream()
                .map(CombDeter::getOpt)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(comb -> {
                            Reference<CardCombination> reference = subst.get(comb.getType());
                            reference.func(v -> {
                                if (v == null) return comb;
                                int comp = comb.compareTo(v);
                                if (comp >= 0) {
                                    return comb;
                                } else {
                                    return v;
                                }
                            });
                        }
                );
        return new HashSet<>(subst.values()).stream().map(Reference::getValue).collect(Collectors.toList());
    }

    public void clear() {
        deters.forEach(CombDeter::clear);
        subst.values().forEach(s -> s.setValue(null));
    }
}
