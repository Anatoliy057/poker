package stud.task.core.command;

import com.google.common.collect.Maps;
import stud.task.core.command.Command;
import stud.task.core.component.Bank;
import stud.task.core.component.Game;
import stud.task.core.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TakeBet implements Command {

    private Map<UUID, Long> prevStorage;

    private List<Player> playerList;
    private Bank bank;
    private Bank.BankState state;

    public TakeBet(List<Player> playerList) {
        this.playerList = playerList;
        prevStorage = new HashMap<>();
    }

    @Override
    public void askGame(Game game) {
        bank = game.getBank();
    }

    @Override
    public void execute() {
        state = bank.getState();
        playerList.forEach(p ->  {
                    prevStorage.put(p.getId(), bank.getBetBy(p.getId()));
                    p.getStorage().take(bank.getBetBy(p.getId()));
                });
    }

    @Override
    public void unexecute() {
        bank.setState(state);
        playerList.forEach(p -> p.getStorage().pull(prevStorage.get(p.getId())));
    }
}
