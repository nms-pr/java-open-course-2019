package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.FizzBuzz;

public class FizzBuzzGame extends NumberGame implements FizzBuzz {

    private static final char DELIMITER = '\n';

    public FizzBuzzGame() {
        super(new FizzBuzzGameStrategy(), new ConsoleOutputController(DELIMITER));
    }

    @Override
    public void print(int from, int to) {
        super.run(from, to);
    }
}
