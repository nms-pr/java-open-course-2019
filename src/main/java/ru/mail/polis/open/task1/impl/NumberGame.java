package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.GameStrategy;
import ru.mail.polis.open.task1.interfaces.OutputController;

public class NumberGame {

    private final GameStrategy strategy;
    private final OutputController outputController;

    public NumberGame(GameStrategy strategy, OutputController outputController){
        this.strategy = strategy;
        this.outputController = outputController;
    }

    public void run(int from, int to){
        for (int number = from; number <=to; number++) {
            outputController.print(strategy.getCorrespondingString(number));
        }
    }


}
