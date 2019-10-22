package stud.task.combination;

import stud.task.card.Card;
import stud.task.combination.determinant.CombDeter;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.util.FactoryObjects;
import stud.task.util.Reference;

import java.util.*;

public class ListComb {

    private static FactoryObjects<CombDeter> fd;

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

    public List<CardCombination> getAllCombs() {
        List<CardCombination> combs = new LinkedList<>();
        deters.forEach(d -> d.getOpt().ifPresent(combs::add));
        return combs;
    }

    public List<CardCombination> getCombs() {
        deters.forEach(d -> {
           Optional<CardCombination> opt = d.getOpt();
           if (opt.isPresent()) {
               CardCombination comb = opt.get();
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
        });
        Set<Reference<CardCombination>> set = new HashSet<>(subst.values());
        List<CardCombination> combs = new LinkedList<>();
        set.forEach(e -> combs.add(e.getValue()));
        return combs;
    }

    public void clear() {
        deters.forEach(CombDeter::clear);
        subst.values().forEach(s -> s.setValue(null));
    }
}
