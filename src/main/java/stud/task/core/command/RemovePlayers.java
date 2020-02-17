package stud.task.core.command;

import stud.task.control.Controller;
import stud.task.core.component.Game;
import stud.task.core.player.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RemovePlayers implements Command {


    private Controller view;

    private List<Player> prevPlayers;
    private Predicate<Long> f;
    private List<Player> playerList;

    @Override
    public void askGame(Game game) {
        playerList = game.getPlayers();
        f = store -> store > game.getBlindBet() * 2;
        view = game.getView();
    }

    @Override
    public void execute() {
        prevPlayers = new LinkedList<>(playerList);
        playerList = playerList.stream()
                .peek(p ->  {
                    if (!f.test(p.getStorage().getSum())) {
                        view.message("Выбыл игрок : " + p.getDeskPlayer());
                    }
                })
                .filter(p -> f.test(p.getStorage().getSum()))
                .collect(Collectors.toList());
    }

    @Override
    public void unexecute() {
        for (Player p :
                prevPlayers) {
            if (!playerList.contains(p)) {
                playerList.add(p);
            }
        }
    }
}
