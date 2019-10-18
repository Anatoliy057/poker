package stud.task.core.player;

import java.util.EventListener;
import java.util.HashMap;

public interface Chooser extends EventListener {

    Action getAction();

    TypeChooser getType();

    void notify(HashMap<String, Object> bet);
}
