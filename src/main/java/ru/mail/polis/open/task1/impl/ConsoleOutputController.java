package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.OutputController;

public class ConsoleOutputController implements OutputController {

    private char delimiter;

    ConsoleOutputController(char delimiter) {

        this.delimiter = delimiter;
    }

    @Override
    public void print(String s) {
        System.out.print(s + delimiter);
    }
}
