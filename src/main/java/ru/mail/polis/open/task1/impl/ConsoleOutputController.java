package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.OutputController;

public class ConsoleOutputController implements OutputController {
    @Override
    public void print(String s, char delimiter) {
        System.out.print(s + delimiter);
    }
}
