package stud.task.core.player;

import stud.task.core.command.Command;

import java.util.Optional;

public class Action {

    private TypeAction type;
    private Optional<Command> optCommand;

    public Action() {
        optCommand = Optional.empty();
    }

    public TypeAction getType() {
        return type;
    }

    public void setType(TypeAction type) {
        this.type = type;
    }

    public Optional<Command> getOptCommand() {
        return optCommand;
    }

    public void setOptCommand(Command optCommand) {
        this.optCommand = Optional.ofNullable(optCommand);
    }
}
