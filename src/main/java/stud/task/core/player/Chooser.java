package stud.task.core.player;

import stud.task.domain.Response;

import java.util.EventListener;
import java.util.HashMap;

public interface Chooser extends EventListener {

    Action getAction();

    TypeChooser getType();

    void notifyStatus(Boolean success);

    void notifyState(Response response);
}
