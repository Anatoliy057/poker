package stud.task.combination;

import stud.task.card.Card;
import stud.task.combination.determinant.CombDeter;
import stud.task.combination.domain.CardCombination;
import stud.task.combination.domain.TypeCombination;
import stud.task.util.Shell;

import java.util.*;

public class ListComb {
    private List<CombDeter> deters;
    private Map<TypeCombination, Shell<CardCombination>> subst;

    public ListComb() {
        FactoryDeters factoryDeters = FactoryDeters.getInstance();
        deters = factoryDeters.create();

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
        deters.forEach(d -> combs.addAll(d.get()));
        return combs;
    }

    public List<CardCombination> getCombs() {
        deters.forEach(d -> {
            List<CardCombination> list = d.get();
            for (CardCombination comb :
                    list) {
                Shell<CardCombination> shell = subst.get(comb.getType());
                shell.func(v -> {
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
        Set<Shell<CardCombination>> set = new HashSet<>(subst.values());
        List<CardCombination> combs = new LinkedList<>();
        set.forEach(e -> combs.add(e.getValue()));
        return combs;
    }
}
