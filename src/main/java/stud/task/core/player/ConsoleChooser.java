package stud.task.core.player;

import stud.task.core.BetException;
import stud.task.core.command.Command;
import stud.task.core.command.DoBet;
import stud.task.core.command.Fold;
import stud.task.core.command.Save;
import stud.task.core.component.GameInfo;
import stud.task.service.ResLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ConsoleChooser implements Chooser {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public Action action(Player p, GameInfo gi) {

        gi.getView().cardPlayer(p);
        gi.getView().cardTable(gi.getTable());
        gi.getView().message(String.format("Bank : %d, Current bet : %d, Max bet : %d, Count players : %d",
                gi.getBank(), gi.getBet(), gi.getMaxBet(), gi.getCountPlayers()));

        boolean unvalid = true;
        TypeAction type = null;
        gi.getView().message(gi.getPossible().toString());
        while (unvalid) {
            gi.getView().message("Выберите действие");
            String temp = scanner.next();
            type = TypeAction.fromString(temp);
            unvalid = type == null || !gi.getPossible().contains(type);
        }

        Command command = null;

        switch (type) {
            case CALL: {
                long bet = gi.getBet();
                try {
                    command = new DoBet(p, bet, gi::checkBet);
                } catch (BetException e) {
                    //ignored
                }
                break;
            }
            case RAISE:
            case BET: {
                unvalid = true;
                long bet = 0;
                while (unvalid) {
                    gi.getView().message("Введите ставку");
                    bet = scanner.nextLong();
                    unvalid = !gi.checkBet(bet);
                }
                try {
                    command = new DoBet(p, bet, gi::checkBet);
                } catch (BetException e) {
                    //ignored
                }
                break;
            }
            case ALL_IN: {
                long bet = Math.min(p.getStorage().getSum(), gi.getMaxBet());
                try {
                    command = new DoBet(p, bet, gi::checkBet);
                } catch (BetException e) {
                    //ignored
                }
                break;
            }
            case FOLD: {
                command = new Fold(p);
            }
            case SAVE: {
                gi.getView().message("Введите название сохранения");
                String name = scanner.next();
                String path = ResLoader.getInstance().getDirectory() + '\\' + name + ".json";
                File file = new File(path);
                try {
                    Writer writer = new FileWriter(file);
                    command = new Save(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                    type = TypeAction.NO;
                }
                System.out.println(file);
            }
        }
        Action action = new Action();
        action.setType(type);
        action.setOptCommand(command);
        return action;
    }

    @Override
    public TypeChooser getType() {
        return TypeChooser.HUMAN;
    }

    @Override
    public Choosers getChooser() {
        return Choosers.CONSOLE;
    }
}
