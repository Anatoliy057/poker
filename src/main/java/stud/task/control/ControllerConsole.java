package stud.task.control;

import stud.task.card.Card;
import stud.task.core.player.Action;
import stud.task.core.player.DeskPlayer;
import stud.task.core.player.Player;
import stud.task.view.ConsoleView;

import java.util.Collection;

public class ControllerConsole implements Controller {

    private ConsoleView view = new ConsoleView();

    @Override
    public void actionBy(Player p, Action a) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getDeskPlayer().getName());
        sb.append(' ');
        sb.append(p.getDeskPlayer().getSurname());
        sb.append(" сделал: ");
        sb.append(a.getType());
        sb.append(' ');
        sb.append(a.getAddition());
        sb.append('.');
        view.printLN(sb);
    }

    @Override
    public void cardTable(Collection<Card> cards) {
        StringBuilder sb = new StringBuilder();
        sb.append("На столе: ");
        enumerationCards(sb, cards);
        view.printLN(sb);
    }

    @Override
    public void cardPlayer(Player p) {
        StringBuilder sb = new StringBuilder();
        sb.append("Карты ");
        playerToString(sb, p.getDeskPlayer());
        sb.append(" :");
        enumerationCards(sb, p.getCards());
        view.printLN(sb);
    }

    @Override
    public void messageBy(Player p, String message) {

    }

    @Override
    public void message(String message) {

    }

    private void enumerationCards(StringBuilder sb, Collection<Card> cards) {
        cards.forEach(c -> {
            sb.append(c.getType());
            sb.append(':');
            sb.append(c.getSuit());
            sb.append(';');
        });
    }

    private void playerToString(StringBuilder sb, DeskPlayer desk) {
        sb.append(desk.getName());
        sb.append(' ');
        sb.append(desk.getSurname());
    }
}
