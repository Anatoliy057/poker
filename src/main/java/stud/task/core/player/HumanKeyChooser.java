package stud.task.core.player;

import stud.task.domain.Response;
import stud.task.view.ConsoleView;

import java.util.Scanner;

public class HumanKeyChooser implements Chooser {

    private TypeChooser type = TypeChooser.HUMAN;

    Scanner scanner = new Scanner(System.in);

    ConsoleView cv = new ConsoleView();

    private long bet = 0;
    private long limit = Long.MAX_VALUE;
    private long purse = 0;
    private boolean success = true;

    @Override
    public Action getAction() {
        while (true) {
            if (!success) cv.printLN("Нверное действие прошлого ответа, повторите попытку");
            cv.printLN("Ваше действие");
            TypeAction type;
            try {
                type = TypeAction.valueOf(scanner.next());
            } catch (IllegalArgumentException e) {
                cv.printLN("Некорректное ключевое слово, повторите");
                continue;
            }
            long additional = 0;
            switch (type) {
                case CALL:
                    additional = bet;
                    break;
                case RAISE:
                    additional = scanner.nextLong();
                    break;
                case FOLD:
                    return new Action(TypeAction.FOLD);
                case ALL_IN:
                    additional = purse;
                    break;
                case YES:
                case NO:
                    break;
            }
            if (checkBet(additional)) {
                return new Action(type, additional);
            } else {
                cv.printLN("Неверное заполнения дополнения, повторите попытку");
            }
        }
    }

    @Override
    public void notifyState(Response response) {
        bet = response.getBet();
        limit = response.getLimit();
        purse = response.getPurse();
        cv.printLN(response);
    }

    @Override
    public void notifyStatus(Boolean success) {
        this.success = success;
    }

    @Override
    public TypeChooser getType() {
        return type;
    }

    private boolean checkBet(long bet) {
        return bet >= this.bet && bet <= limit
                && bet <= purse;
    }
}
