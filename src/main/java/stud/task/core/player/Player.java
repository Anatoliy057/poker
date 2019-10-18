package stud.task.core.player;

import stud.task.card.Card;
import stud.task.combination.domain.CardCombination;

import java.util.Collection;
import java.util.List;

public interface Player {

    Storage getStorage();

    DeskPlayer getDeskPlayer();

    Chooser getChooser();

    Collection<Card> getCards();

    boolean isCan();

    List<CardCombination> getCombs();

    void addCard(Card card);

    default void addAllCard(Collection<Card> card) {
        card.forEach(this::addCard);
    }

    void clear();

}
