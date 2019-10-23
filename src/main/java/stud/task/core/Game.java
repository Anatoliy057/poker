package stud.task.core;

import stud.task.card.DeckCards;
import stud.task.combination.determinant.CombDeter;
import stud.task.control.Controller;
import stud.task.control.ControllerConsole;
import stud.task.core.player.*;
import stud.task.core.stage.*;
import stud.task.util.FactoryObjects;

import java.util.*;

public class Game {

    private FactoryObjects<Stage> fs;
    private FactoryObjects<CombDeter> fc;

    private List<Stage> stages;
    private List<Player> players = new LinkedList<>();
    private GameItems items;
    private Bank bank;
    private Controller controller = new ControllerConsole();
    private DeckCards deck = new DeckCards();

    private int pls = 2;
    private long purse = 300;

    public Game() {
        fs = new FactoryObjects<>("stage/stage.json");
        fc = new FactoryObjects<>("combination/deters.json");
        stages = fs.createList();
        for (int i = 0; i < 6; i++) {
            players.add(createPlayer());
        }
        LinkedList<UUID> ids = new LinkedList<>();
        players.forEach(p -> ids.add(p.getId()));
        bank = new Bank(ids);
        items = new GameItems(bank, controller, players, deck);
    }

    public void start() {
        while (!players.isEmpty()) {
            for (Stage s :
                    stages) {
                s.start(items);
            }
        }
    }

    private Player createPlayer() {
        Storage storage = new Storage(300);
        Chooser ch = new HumanKeyChooser();
        List<CombDeter> l = fc.createList();
        return new Player(storage, ch, l);
    }
}
