package stud.task.core.command;

import javassist.NotFoundException;

import java.util.*;

public class Commander {

    private Stack<Command> commandStack;
    private Map<String, Integer> points;

    public Commander() {
        commandStack = new Stack<>();
        points = new LinkedHashMap<>();
    }

    public void savePoint(String name) {
        points.put(name, commandStack.size());
    }

    public void loadPoint(final String name) throws NotFoundException {
        int point = Optional.ofNullable(points.get(name))
                .orElseThrow(() ->
                        new NotFoundException("Not found point by name: " + name)
                );
        int diff = commandStack.size() - point;
        for (int i = 0; i < diff; i++) {
            commandStack.pop().unexecute();
        }
    }

    public Set<String> getSaves() {
        return points.keySet();
    }

    public void push(Command command) {
        command.execute();
        commandStack.push(command);
    }

    public Command pop() {
        Command command = commandStack.pop();
        command.unexecute();
        return command;
    }

    public List<Command> history() {
        LinkedList<Command> history = new LinkedList<>();
        commandStack.forEach(history::addFirst);
        return history;
    }
}
