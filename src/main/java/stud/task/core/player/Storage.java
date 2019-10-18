package stud.task.core.player;

public interface Storage extends Cloneable {

    boolean takeAway(double sum);

    void give(double sum);

    double getPurse();

    double takeAll();

    Storage clone();
}
