package stud.task.core.player;

import org.json.*;
import stud.task.card.Card;
import stud.task.combination.ListComb;
import stud.task.combination.determinant.CombDeter;
import stud.task.combination.domain.CardCombination;

import java.util.*;

public class Player implements JSONString {

    private UUID id;
    private Storage storage;
    private Chooser chooser;
    private DeskPlayer deskPlayer;
    private List<Card> cards;
    private List<Card> allCards;

    private ListComb combs;

    public Player(JSONObject o, List<CombDeter> deters) {
        combs = new ListComb(deters);

        id = UUID.fromString(o.getString("id"));
        long store = o.getLong("store");
        String name = o.getString("name");
        String surname = o.getString("surname");

        storage = new Storage(store);
        deskPlayer = new DeskPlayer(surname, name);
        System.out.println(o.getString("choose"));
        chooser = Choosers.valueOf(o.getString("choose")).getChooser();


        cards = new LinkedList<>();
        allCards = new LinkedList<>();
        JSONArray arr = o.getJSONArray("cards");
        for (int i = 0; i < arr.length(); i++) {
            Card c = new Card(arr.getJSONObject(i));
            cards.add(c);
            combs.add(c);
        }
    }

    public Player(Storage storage, Chooser chooser, DeskPlayer deskPlayer, List<CombDeter> deters) {
        this.storage = storage;
        this.deskPlayer = deskPlayer;
        this.chooser = chooser;
        allCards = new LinkedList<>();
        combs = new ListComb(deters);
        cards = new LinkedList<>();
        id = UUID.randomUUID();
    }

    public Player(Storage storage, Chooser chooser, List<CombDeter> deters) {
        this.storage = storage;
        this.chooser = chooser;
        allCards = new LinkedList<>();
        combs = new ListComb(deters);
        cards = new LinkedList<>();
        id = UUID.randomUUID();
        deskPlayer = DeskPlayer.randomInstance();
    }

    public Storage getStorage() {
        return storage;
    }

    public DeskPlayer getDeskPlayer() {
        return deskPlayer;
    }

    public Chooser getChooser() {
        return chooser;
    }

    public List<Card> getCards() {
        return cards;
    }

    public UUID getId() {
        return id;
    }

    public void addPlayerCard(Card card) {
        allCards.add(card);
        cards.add(card);
        combs.add(card);
    }

    public void addPlayerCardAll(Collection<Card> cards) {
        allCards.addAll(cards);
        this.cards.addAll(cards);
        combs.addAll(cards);
    }

    public void addCard(Card card) {
        allCards.add(card);
        combs.add(card);
    }

    public void addCardAll(Collection<Card> cards) {
        allCards.addAll(cards);
        combs.addAll(cards);
    }

    public boolean removeCard(Card card) {
        allCards.remove(card);
        return combs.remove(card);
    }

    public boolean removeCardAll(Collection<Card> cards) {
        allCards.removeAll(cards);
        return combs.removeAll(cards);
    }

    public boolean removePlayerCard(Card card) {
        allCards.remove(card);
        return cards.remove(card) & combs.remove(card);
    }

    public boolean removePlayerCardAll(Collection<Card> cards) {
        allCards.removeAll(cards);
        return this.cards.removeAll(cards) & combs.removeAll(cards);
    }

    public List<CardCombination> getCombs() {
        return combs.getAllCombs();
    }

    public void clear() {
        combs.clear();
        cards.clear();
    }

    public PlayerState getState() {
        return new PlayerState(this);
    }

    public void load(PlayerState state, List<CombDeter> deters) {
        allCards = state.getAllCards();
        cards = state.getCards();
        chooser = state.getChooser();
        storage = state.getStore();
        combs = new ListComb(deters);
        combs.addAll(allCards);
        deskPlayer = state.getDesk();
        id = state.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("deskPlayer=").append(deskPlayer);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toJSONString() {
        JSONWriter writer = new JSONStringer();
        writer.object()
                .key("id").value(id)
                .key("choose").value(chooser.getChooser());
        writer.key("cards").array();
        cards.forEach(writer::value);
        return writer.endArray()
                .key("store").value(storage.getSum())
                .key("name").value(deskPlayer.getName())
                .key("surname").value(deskPlayer.getSurname())
                .endObject().toString();
    }

    public class PlayerState {
        private List<Card> allCards;
        private List<Card> cards;
        private long store;
        private UUID id;
        private DeskPlayer desk;
        private Choosers ch;

        public PlayerState(Player p) {
            allCards = new LinkedList<>(p.allCards);
            cards = new LinkedList<>(p.cards);
            store = p.getStorage().getSum();
            id = p.getId();
            desk = p.getDeskPlayer();
            ch = p.getChooser().getChooser();
        }

        public List<Card> getAllCards() {
            return new LinkedList<>(allCards);
        }

        public List<Card> getCards() {
            return new LinkedList<>(cards);
        }

        public Storage getStore() {
            return new Storage(store);
        }

        public UUID getId() {
            return id;
        }

        public DeskPlayer getDesk() {
            return desk;
        }

        public Chooser getChooser() {
            return ch.getChooser();
        }
    }
}
