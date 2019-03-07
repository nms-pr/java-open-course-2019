package ru.mail.polis.open.task1;

import ru.mail.polis.open.task1.impl.FizzBuzzGame;
import ru.mail.polis.open.task1.interfaces.FizzBuzz;

public class Main {

    public static void main(String[] args) {
        FizzBuzz game = new FizzBuzzGame();
        game.print(0, 100);
    }
}
