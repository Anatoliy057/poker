package stud.task.core;

import org.json.JSONObject;
import stud.task.core.component.*;
import stud.task.combination.determinant.CombDeter;
import stud.task.control.Controller;
import stud.task.control.ControllerConsole;
import stud.task.core.player.*;
import stud.task.core.stage.*;
import stud.task.service.JsonResource;
import stud.task.service.ResLoader;
import stud.task.util.FactoryObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EngineGame {

    private FactoryObjects<Stage> fs;

    private List<Stage> stages;
    private Game game;

    public EngineGame() throws IOException {

        fs = new FactoryObjects<>("stage/stage.json");
        stages = fs.createList();
        GameBuilder builder = new GameBuilder(new ControllerConsole());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Загрузить ?");

        if (scanner.nextBoolean()) {
            System.out.println("название сохранения :");
            String s = scanner.next();
            JsonResource jr = new JsonResource();
            JSONObject obj = jr.objectFromFile(s);
            game = builder.build(obj, new ControllerConsole());
        } else {
            Properties prop = new Properties();
            prop.load(new FileReader(ResLoader.getInstance().getFile("game/game.properties")));
            game = builder.build(prop);
        }
    }

    public void start() {
        while (game.getPlayers().size() != 1) {
            TypeStage last = null;
            for (Stage s :
                    stages) {
                System.out.println(game.getCurStage() + " " + game.getPrevStage() + " " + s.type());
                if (game.getCurStage() != s.type()) {
                    last = s.type();
                    continue;
                }
                else if (last != game.getPrevStage()) {
                    last = s.type();
                    continue;
                }
                last = s.type();
                s.start(game);
            }
        }
    }
}
