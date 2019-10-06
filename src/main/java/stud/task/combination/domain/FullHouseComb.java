package stud.task.combination.domain;

import stud.task.card.TypeCard;

public class FullHouseComb extends AbstractCardComb {

    private final int lvl1, lvl2;

    public FullHouseComb(int lvl1, int lvl2) {
        super(TypeCombination.FULL_HOUSE);
        this.lvl1 = lvl1;
        this.lvl2 = lvl2;
    }

    public FullHouseComb(TypeCard lvl1, TypeCard lvl2) {
        this(lvl1.getLvl(), lvl2.getLvl());
    }

    @Override
    protected int deepCompareTo(CardCombination o) {
        FullHouseComb comb = (FullHouseComb) o;
        int comp = getLvl1() - comb.getLvl1();
        if (comp == 0) {
            return getLvl2() - comb.getLvl2();
        } else
            return comp;
    }

    public int getLvl1() {
        return lvl1;
    }

    public int getLvl2() {
        return lvl2;
    }
}
