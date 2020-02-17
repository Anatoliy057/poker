package stud.task.core.component;

import org.json.*;
import stud.task.card.Card;
import stud.task.card.SuitCard;
import stud.task.card.TypeCard;
import stud.task.core.player.DeskPlayer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DeckCards implements JSONString {

    private LinkedList<Card> deck;

    public DeckCards() {
        deck = new LinkedList<>();
        load();
    }

    public DeckCards(JSONObject o) {
        deck = new LinkedList<>();
        JSONArray arr = o.getJSONArray("cards");
        for (int i = 0; i < arr.length(); i++) {
            deck.add(new Card(arr.getJSONObject(i)));
        }
    }

    public DeckCards(DeckState state) {
        deck = new LinkedList<>(state.getCards());
    }

    public void load(DeckState state) {
        deck = new LinkedList<>(state.getCards());
    }

    public void reset() {
        deck.clear();
        load();
    }

    private void clear() {
        deck.clear();
    }

    private void load() {
        for (TypeCard l:
                TypeCard.values()) {
            for (SuitCard s :
                    SuitCard.values()) {
                deck.add(new Card(l, s));
            }
        }
        Collections.shuffle(deck);
    }

    public Card poll() {
        return deck.poll();
    }

    public void push(Card card) {
        deck.addLast(card);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public DeckState getState() {
        return new DeckState(deck);
    }

    @Override
    public String toJSONString() {
        JSONWriter writer = new JSONStringer();
        writer.object().key("cards").array();
        deck.forEach(writer::value);
        return writer.endArray().endObject().toString();
    }

    public class DeckState {
        private LinkedList<Card> cards;

        private DeckState(List<Card> cards) {
            this.cards = new LinkedList<>(cards);
        }

        public LinkedList<Card> getCards() {
            return cards;
        }
    }

}
