package stud.task.core.player;

import java.util.HashMap;
import java.util.Scanner;

public class HumanKeyChooser implements Chooser {

    private double currentBet = 0;
    private double bank = 0;
    private TypeChooser type = TypeChooser.HUMAN;

    Scanner scanner = new Scanner(System.in);

    @Override
    public Action getAction() {
        TypeAction action = TypeAction.valueOf(scanner.next());
        Object o = scanner.next();
        return new Action(action, o);
    }

    @Override
    public void notify(HashMap<String, Object> items) {
        currentBet = (Double) items.get("currentBet");
        bank = (Double) items.get("bank");
    }

    @Override
    public TypeChooser getType() {
        return null;
    }
}
