package stud.task.core;

import stud.task.card.DeckCards;
import stud.task.control.Controller;
import stud.task.control.ControllerConsole;
import stud.task.core.player.*;
import stud.task.core.stages.*;
import stud.task.util.FactoryObjects;

import java.util.*;

public class Game {

    private FactoryObjects<Stage> fs;

    private List<Stage> stages;
    private List<Player> players;
    private GameItems items;
    private Bank bank;
    private Controller controller;
    private DeckCards deck;

    private int pls = 2;
    private long purse = 100;

    public Game() {

    }

    public void start() {

    }
}
