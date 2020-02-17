package stud.task.core;

public class BetException extends ActionException {

    public BetException(long bet, long curBet) {
        this(String.format("Bet made below current: {bet: %d; curBet: %d", bet, curBet));
    }

    public BetException(long bet) {
        this("Bet does not fulfill current requirements: " + bet);
    }

    public BetException(String message) {
        super(message);
    }
}
