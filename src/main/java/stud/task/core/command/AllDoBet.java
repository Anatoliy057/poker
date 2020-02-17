package stud.task.core.command;

import stud.task.core.BetException;
import stud.task.core.component.Game;

import java.util.Stack;

public class AllDoBet implements Command {

    private long bet;
    private Game game;
    private Stack<Command> commandStack;

    public AllDoBet(long bet) {
        commandStack = new Stack<>();
        this.bet = bet;
    }

    @Override
    public void askGame(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.getPlayers().forEach(p -> {
            try {
                DoBet c = new DoBet(p, bet, game::checkBet);
                c.askGame(game);
                c.execute();
                commandStack.push(c);
            } catch (BetException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void unexecute() {
        while (!commandStack.empty())
            commandStack.pop().unexecute();
    }
}
