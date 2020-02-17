package stud.task.core.command;

import stud.task.core.component.Game;
import stud.task.core.component.TransactionGame;
import stud.task.core.player.Player;

import java.util.List;

public class Fold implements Command {

    private Game game;
    private Player player;
    private int index;
    private List<Player> playerList;

    public Fold(Player player) {
        this.player = player;
    }

    @Override
    public void askGame(Game game) {
        playerList = game.getCurPlayers();
        this.game = game;
    }

    @Override
    public void execute() {
        index = playerList.indexOf(player);
        playerList.remove(player);
        if (game.getTarget() > index) {
            game.prevTarget();
        }
    }

    @Override
    public void unexecute() {
        playerList.add(index, player);
        if (game.getTarget() > index) {
            game.nextTarget();
        }
    }
}
