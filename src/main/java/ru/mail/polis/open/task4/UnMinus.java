package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

public class UnMinus implements Expr {

    private final @NotNull int value;

    public UnMinus(final @NotNull int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return -value;
    }
}
