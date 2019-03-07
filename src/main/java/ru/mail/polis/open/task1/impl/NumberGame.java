package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.GameStrategy;
import ru.mail.polis.open.task1.interfaces.OutputController;

class NumberGame {

    private final GameStrategy strategy;
    private final OutputController outputController;

    NumberGame(GameStrategy strategy, OutputController outputController){
        this.strategy = strategy;
        this.outputController = outputController;
    }

    void run(int from, int to){
        for (int number = from; number <=to; number++) {
            outputController.print(strategy.getCorrespondingString(number));
        }
    }


}
