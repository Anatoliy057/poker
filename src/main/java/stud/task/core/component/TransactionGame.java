package stud.task.core.component;

import stud.task.core.stage.TypeStage;

import java.util.*;

public class TransactionGame {

    private final Game game;

    private Queue<Operation> operations;
    private Stack<Operation> prevOperations;

    public TransactionGame(Game game) {
        this.game = game;
        operations = new LinkedList<>();
        prevOperations = new Stack<>();
    }

    public void updateStage(TypeStage cur) {
        Operation op = new Operation() {

            TypeStage oldCur, oldPrev;

            @Override
            public boolean _do() {
                oldCur = game.getCurStage();
                oldPrev = game.getPrevStage();
                game.updateStage(cur);
                return true;
            }

            @Override
            public void _undo() {
                game.setCurStage(oldCur);
                game.setPrevStage(oldPrev);
            }
        };
        operations.add(op);
    }

    public void resetCurBet() {
        Operation op = new Operation() {
            private long prevBet;

            @Override
            public boolean _do() {
                prevBet = game.getCurBet();
                game.setCurBet(0);
                return true;
            }

            @Override
            public void _undo() {
                game.setCurBet(prevBet);
            }
        };
        operations.add(op);
    }

    public void updateCurBet(long bet) {
        Operation op = new Operation() {
            private long prevBet;

            @Override
            public boolean _do() {
                prevBet = game.getCurBet();
                game.updateCurBet(bet);
                return true;
            }

            @Override
            public void _undo() {
                game.setCurBet(prevBet);
            }
        };
        operations.add(op);
    }

    public void updateMaxBet() {
        Operation op = new Operation() {
            private long prevBet;

            @Override
            public boolean _do() {
                prevBet = game.getCurMaxBet();
                game.updateMaxBet();
                return true;
            }

            @Override
            public void _undo() {
                game.setCurMaxBet(prevBet);
            }
        };
        operations.add(op);
    }

    public boolean commit() {
        while (!operations.isEmpty()) {
            Operation op = operations.poll();
            if (op._do()) {
                prevOperations.push(op);
            } else {
                while (!prevOperations.empty()) {
                    prevOperations.pop()._undo();
                }
                return false;
            }
        }
        return true;
    }

    public void reset() {
        while (!prevOperations.empty()) {
            prevOperations.pop()._undo();
        }
    }

    private interface Operation {

        boolean _do();

        void _undo();
    }
}
