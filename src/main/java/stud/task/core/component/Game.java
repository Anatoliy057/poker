package stud.task.core.component;

import javassist.NotFoundException;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONWriter;
import stud.task.card.Card;
import stud.task.control.Controller;
import stud.task.core.command.Command;
import stud.task.core.command.Commander;
import stud.task.core.player.Choosers;
import stud.task.core.player.Player;
import stud.task.core.stage.TypeStage;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game implements JSONString {

    private Controller view;

    private Commander commander;

    private TypeStage prevStage;
    private TypeStage curStage;

    private Bank bank;
    private List<Card> table;
    private DeckCards deck;

    private List<Player> players;
    private List<Player> curPlayers;
    private int target;

    private long maxBet;
    private long curBet;
    private long curMaxBet;
    private long blindBet;

    public Game(Bank bank, DeckCards deck, List<Player> players, long maxBet, long blindBet) {
        this.bank = bank;
        this.deck = deck;
        this.players = players;
        this.maxBet = maxBet;
        this.blindBet = blindBet;

        curPlayers = new ArrayList<>(players);
        table = new LinkedList<>();
        target = 0;

        updateMaxBet();
        curBet = 0;

        commander = new Commander();
    }

    Game() {}

    public void updateStage(TypeStage cur) {
        if (cur != TypeStage.BLIND)
            prevStage = curStage;
        else prevStage = null;
        curStage = cur;
    }

    public void updateCurBet(long bet) {
        curBet = bet > curBet ? bet : curBet;
    }

    public void updateMaxBet() {
        curMaxBet = curPlayers.stream()
                .mapToLong(p -> p.getStorage().getSum())
                .min()
                .orElse(-1);
        curMaxBet = curMaxBet > maxBet ? maxBet : curMaxBet;
    }

    public boolean checkBet(long bet) {
        return bet >= curBet && bet <= curMaxBet;
    }

    public void doCommand(Command command) {
        command.askGame(this);
        commander.push(command);
    }

    public Player nextTPlayer() {
        Player p = curPlayers.get(target);
        nextTarget();
        return p;
    }

    public void nextTarget() {
        target = (target + 1) % curPlayers.size();
    }

    public void prevTarget() {
        target = Math.abs(target - 1) % curPlayers.size();
    }

    public Player getTargetPlayer() {
        return curPlayers.get(target);
    }

    public void save(String save) {
        commander.savePoint(save);
    }

    public Set<String> getSaves() {
        return commander.getSaves();
    }

    public void load(String save) throws NotFoundException {
        commander.loadPoint(save);
    }

    public TransactionGame getTransaction() {
        return new TransactionGame(this);
    }

    public GameInfo getInfo() {
        return new GameInfo(this);
    }

    /*----------------------------------------------------
    Package-private Getters and setters
    ----------------------------------------------------*/

    public Controller getView() {
        return view;
    }

    public void setView(Controller view) {
        this.view = view;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public TypeStage getPrevStage() {
        return prevStage;
    }

    public void setPrevStage(TypeStage prevStage) {
        this.prevStage = prevStage;
    }

    public TypeStage getCurStage() {
        return curStage;
    }

    public void setCurStage(TypeStage curStage) {
        this.curStage = curStage;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Card> getTable() {
        return table;
    }

    public void setTable(List<Card> table) {
        this.table = table;
    }

    public DeckCards getDeck() {
        return deck;
    }

    public void setDeck(DeckCards deck) {
        this.deck = deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getCurPlayers() {
        return curPlayers;
    }

    public void setCurPlayers(List<Player> curPlayers) {
        this.curPlayers = curPlayers;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public long getMaxBet() {
        return maxBet;
    }

    public void setMaxBet(long maxBet) {
        this.maxBet = maxBet;
    }

    public long getCurBet() {
        return curBet;
    }

    public void setCurBet(long curBet) {
        this.curBet = curBet;
    }

    public long getCurMaxBet() {
        return curMaxBet;
    }

    public void setCurMaxBet(long curMaxBet) {
        this.curMaxBet = curMaxBet;
    }

    public long getBlindBet() {
        return blindBet;
    }

    public void setBlindBet(long blindBet) {
        this.blindBet = blindBet;
    }

    @Override
    public String toJSONString() {
        JSONWriter writer = new JSONStringer();
        writer.object()
                .key("prevStage").value(prevStage)
                .key("curStage").value(curStage)
                .key("table").array();
        table.forEach(writer::value);
        writer.endArray()
                .key("deck").value(deck)
                .key("bank").value(bank);
        writer.key("players").array();
        players.forEach(writer::value);
        writer.endArray().key("curPlayers").array();
        curPlayers.forEach(c -> writer.value(c.getId()));
        return writer.endArray()
                .key("target").value(target)
                .key("maxBet").value(maxBet)
                .key("curBet").value(curBet)
                .key("curMaxBet").value(curMaxBet)
                .key("blindBet").value(blindBet)
                .endObject()
                .toString();
    }

    public static Set<String> keys() {
        return Stream.of(new String[] {
                "prevStage", "curStage", "table", "deck",
                "bank", "players", "curPlayers", "target",
                "maxBet", "curBet", "curMaxBet", "blindBet"
        }).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Game.class.getSimpleName() + "[", "]")
                .add("view=" + view)
                .add("commander=" + commander)
                .add("prevStage=" + prevStage)
                .add("curStage=" + curStage)
                .add("bank=" + bank)
                .add("table=" + table)
                .add("deck=" + deck)
                .add("players=" + players)
                .add("curPlayers=" + curPlayers)
                .add("target=" + target)
                .add("maxBet=" + maxBet)
                .add("curBet=" + curBet)
                .add("curMaxBet=" + curMaxBet)
                .add("blindBet=" + blindBet)
                .toString();
    }
}
