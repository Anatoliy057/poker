package stud.task.core.command;

import stud.task.core.component.Game;

import java.io.IOException;
import java.io.Writer;

public class Save implements Command {

    private Writer writer;
    private String json;

    public Save(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void askGame(Game game) {
        json = game.toJSONString();
    }

    @Override
    public void execute() {
        try {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unexecute() {
        //nothink
    }
}
