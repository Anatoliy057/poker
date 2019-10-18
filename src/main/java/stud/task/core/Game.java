package stud.task.core;

import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.control.ControllerConsole;
import stud.task.core.player.*;
import stud.task.core.stages.*;

import java.util.*;

public class Game {

    private FactoryStages fs = FactoryStages.getInstance();

    private List<Stage> stages;
    private List<Player> players;
    private GameItems items;
    private Bank bank;
    private Controller controller;
    private DeckCards deck;

    private int pls = 2;
    private double purse = 100;

    public Game() {
        bank = new Bank();
        stages = fs.create();
        players = new LinkedList<>();
        controller = new ControllerConsole();
        deck = new DeckCards();
        for (int i = 0; i < 2; i++) {
            Storage s = new SimpleStorage(purse);
            Player p = new SimplePlayer(s, new HumanKeyChooser(), null);
            players.add(p);
        }
        items = new GameItems(bank, controller, players, deck);
    }

    public void start() {
        while (!players.isEmpty()) {
            for (Stage s:
                    stages) {
                s.start(items);
            }
        }
    }
}
