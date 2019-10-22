package stud.task.combination.domain;

import stud.task.card.Card;

import java.util.Collection;
import java.util.List;

public interface CardCombination extends Comparable<CardCombination> {

    TypeCombination getType();

    List<Card> getCards();
}
