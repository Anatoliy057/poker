package stud.task.control;

import stud.task.card.Card;
import stud.task.core.player.DeskPlayer;
import stud.task.core.player.Player;
import stud.task.view.ConsoleView;

import java.util.Collection;

public class ControllerConsole implements Controller {

    private ConsoleView view = new ConsoleView();

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
        StringBuilder sb = new StringBuilder();
        sb.append("Игрок ")
                .append(p.getDeskPlayer().getName())
                .append(' ')
                .append(p.getDeskPlayer().getSurname())
                .append(':')
                .append(' ')
                .append(message);
        view.printLN(sb);
    }

    @Override
    public void message(String message) {
        view.printLN(message);
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
