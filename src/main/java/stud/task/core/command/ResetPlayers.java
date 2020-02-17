package stud.task.core.command;

import stud.task.combination.determinant.CombDeter;
import stud.task.core.component.Game;
import stud.task.core.player.Player;
import stud.task.util.FactoryObjects;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ResetPlayers implements Command {

    private List<Player.PlayerState> states;
    private FactoryObjects<CombDeter> fo = new FactoryObjects<>("combination/deters.json");
    private List<Player> players;

    @Override
    public void askGame(Game game) {
        players = game.getPlayers();
    }

    @Override
    public void execute() {
        states = new LinkedList<>();
        players.forEach(p -> {
            states.add(p.getState());
            p.clear();
        });
    }

    @Override
    public void unexecute() {
        Iterator<Player> p = players.iterator();
        Iterator<Player.PlayerState> s = states.iterator();
        while (p.hasNext() && s.hasNext()) {
            p.next().load(s.next(), fo.createList());
        }
    }
}
