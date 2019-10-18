package stud.task.combination.domain;

import stud.task.card.Card;

import java.util.Collection;

public interface CardCombination extends Comparable<CardCombination> {

    TypeCombination getType();

    Collection<Card> getCards();
}
