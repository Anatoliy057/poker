package stud.task.core;

import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.core.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class GameItems {

    private HashMap<String, Object> items;

    private Bank bank;
    private Controller controller;
    private List<Player> players;
    private DeckCards deck;

    public GameItems(Bank bank, Controller controller, List<Player> players, DeckCards deck) {
        this.bank = bank;
        this.controller = controller;
        this.players = players;
        this.deck = deck;
    }

    public Bank getBank() {
        return bank;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Controller getController() {
        return controller;
    }

    public Optional<Object> getItem(String key) {
        return Optional.of(items.get(key));
    }

    public Optional<Object> pullItem(String key, Object item) {
        return Optional.ofNullable(items.put(key, item));
    }

    public HashMap<String, Object> getItems() {
        return new HashMap<>(this.items);
    }

    public DeckCards getDeck() {
        return deck;
    }
}
