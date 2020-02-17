package stud.task.core.component;

import org.json.JSONArray;
import org.json.JSONObject;
import org.reflections.ReflectionsException;
import stud.task.card.Card;
import stud.task.combination.determinant.CombDeter;
import stud.task.control.Controller;
import stud.task.core.command.Commander;
import stud.task.core.player.*;
import stud.task.core.stage.TypeStage;
import stud.task.util.FactoryObjects;

import java.util.*;
import java.util.stream.Collectors;

public class GameBuilder {

    private Controller view;

    private Commander commander;

    private TypeStage prevStage;
    private TypeStage curStage;

    private Bank bank;
    private List<Card> table;
    private DeckCards deck;

    private List<Player> players;
    private List<Player> curPlayers;
    private int target = -1;

    private long maxBet;
    private long curBet;
    private long curMaxBet;
    private long blindBet;

    private FactoryObjects<CombDeter> fo;

    public GameBuilder(Controller controller) {
        players = new LinkedList<>();
        curPlayers = new LinkedList<>();
        maxBet = -1;
        curBet = 0;
        curMaxBet = -1;
        blindBet = -1;

        setController(controller);

        fo = new FactoryObjects<>("combination/deters.json");
    }

    public GameBuilder setController(Controller view) {
        this.view = view;
        return this;
    }

    public GameBuilder setCommander(Commander commander) {
        this.commander = commander;
        return this;
    }

    public GameBuilder setBank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public GameBuilder setTable(List<Card> table) {
        this.table = table;
        return this;
    }

    public GameBuilder setDeck(DeckCards deck) {
        this.deck = deck;
        return this;
    }

    public GameBuilder addPlayer(Player p) {
        players.add(p);
        return this;
    }

    public GameBuilder addCurPlayer(Player p) {
        curPlayers.add(p);
        return this;
    }

    public GameBuilder setPlayers(List<Player> players) {
        this.players.clear();
        this.players.addAll(players);
        return this;
    }

    public GameBuilder setCurPlayers(List<Player> curPlayers) {
        this.curPlayers.clear();
        this.curPlayers.addAll(curPlayers);
        return this;
    }

    public GameBuilder setTarget(int target) {
        this.target = target;
        return this;
    }

    public GameBuilder setMaxBet(long maxBet) {
        this.maxBet = maxBet;
        return this;
    }

    public GameBuilder setCurBet(long curBet) {
        this.curBet = curBet;
        return this;
    }

    public GameBuilder setCurMaxBet(long curMaxBet) {
        this.curMaxBet = curMaxBet;
        return this;
    }

    public GameBuilder setBlindBet(long blindBet) {
        this.blindBet = blindBet;
        return this;
    }

    public GameBuilder setStages(TypeStage prev, TypeStage cur) {
        prevStage = prev;
        curStage = cur;
        return this;
    }

    public GameBuilder addPlayer(DeskPlayer desk, long store, Choosers ch) {
        return addPlayer(new Player(new Storage(store), ch.getChooser(), desk, fo.createList()));
    }

    public GameBuilder addBot(DeskPlayer desk, long store, Choosers ch) {
        return addPlayer(new Player( new Storage(store), ch.getChooser(), desk, fo.createList()));
    }

    public Game build(JSONObject obj, Controller controller) throws InvalidPropertiesFormatException {
        if (!obj.keySet().containsAll(Game.keys()))
            throw new InvalidPropertiesFormatException("Save must have" + Game.keys());
        setController(controller)
                .setStages(TypeStage.valueOf(obj.getString("prevStage")),
                        (TypeStage.valueOf(obj.getString("curStage"))))
                .setBlindBet(obj.getLong("blindBet"))
                .setCurBet(obj.getLong("curBet"))
                .setCurMaxBet(obj.getLong("curMaxBet"))
                .setMaxBet(obj.getLong("maxBet"))
                .setBank(new Bank(obj.getJSONObject("bank")))
                .setDeck(new DeckCards(obj.getJSONObject("deck")));
        JSONArray arr = obj.getJSONArray("table");

        List<Card> cards = new LinkedList<>();
        for (int i = 0; i < arr.length(); i++) {
            cards.add(new Card(arr.getJSONObject(i)));
        }
        setTable(cards);

        arr = obj.getJSONArray("players");
        Map<UUID, Player> map = new HashMap<>();
        for (int i = 0; i < arr.length(); i++) {
            Player p = new Player(arr.getJSONObject(i), fo.createList());
            addPlayer(p);
            map.put(p.getId(), p);
        }
        setTarget(obj.getInt("target"));
        List<Player> curPlayers = new LinkedList<>();
        arr = obj.getJSONArray("curPlayers");
        for (int i = 0; i < arr.length(); i++) {
            UUID id = UUID.fromString(arr.getString(i));
            curPlayers.add(map.get(id));
        }
        setCurPlayers(curPlayers);
        curPlayers.forEach(p -> p.addCardAll(table));
        return build();
    }

    public Game build(Properties prop) {
        int players = Integer.valueOf(prop.getProperty("players"));
        int bots = Integer.valueOf(prop.getProperty("bots"));
        Choosers chooserBot = Choosers.valueOf(prop.getProperty("chooser.bot"));
        Choosers chooserPlayer = Choosers.valueOf(prop.getProperty("chooser.player"));
        long store = Long.valueOf(prop.getProperty("store"));
        long blind = Long.valueOf(prop.getProperty("blind"));

        setBlindBet(blind);

        for (int i = 0; i < bots; i++) {
            addBot(DeskPlayer.randomInstance(), store, chooserBot);
        }

        if (players == 1) {
            DeskPlayer desk = DeskPlayer.randomInstance();
            String name = (String) prop.getOrDefault("player.name", desk.getName());
            String surname = (String) prop.getOrDefault("player.surname", desk.getSurname());
            long mystore = Long.valueOf(prop.getOrDefault("player.mystore", store).toString());
            boolean first = Boolean.valueOf(prop.getOrDefault("player.first", false).toString());
            addPlayer(new DeskPlayer(name, surname), mystore, chooserPlayer);
            if (first) Collections.reverse(this.players);
        } else {
            for (int i = 0; i < players; i++) {
                addPlayer(DeskPlayer.randomInstance(), store, chooserPlayer);
            }
        }
        return build();
    }

    public Game build() {
        Game game = new Game();
        try {
            if (commander == null)
                commander = new Commander();
            if (curPlayers == null || curPlayers.isEmpty())
                curPlayers = new LinkedList<>(players);

            if (bank == null)
                bank = new Bank(curPlayers.stream()
                        .map(Player::getId)
                        .collect(Collectors.toList())
                );
            if (table == null)
                table = new LinkedList<>();
            if (deck == null)
                deck = new DeckCards();
            if (maxBet == -1)
                maxBet = curPlayers.stream()
                        .mapToLong(p -> p.getStorage().getSum())
                        .max()
                        .orElse(-1);
            if (curMaxBet == -1)
                curMaxBet = Math.max(maxBet, curPlayers.stream()
                        .mapToLong(p -> p.getStorage().getSum())
                        .max()
                        .orElse(-1));
            if (target == -1)
                target = 0;
            if (curStage == null)
                curStage = TypeStage.BLIND;
            else if (prevStage == null)
                throw new ReflectionsException("\"PrevStage\" parameter not set then \"CurStage\" do");
            if (view == null)
                throw new ReflectionsException("Controller not set");
            if (blindBet == -1)
                throw new ReflectionsException("\"BlindBet\" parameter not set");
        } catch (NullPointerException e) {
            throw new ReflectionsException("Required parameter not set", e);
        }
        game.setBank(bank);
        game.setCommander(commander);
        game.setView(view);
        game.setBlindBet(blindBet);
        game.setMaxBet(maxBet);
        game.setCurBet(curBet);
        game.setDeck(deck);
        game.setPlayers(players);
        game.setCurPlayers(curPlayers);
        game.setTarget(target);
        game.setTable(table);
        game.setCurMaxBet(curMaxBet);
        game.setCurStage(curStage);
        game.setPrevStage(prevStage);
        return game;
    }
}
