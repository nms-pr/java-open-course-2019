package ru.mail.polis.open.task4.expressions;

import ru.mail.polis.open.task4.Expr;

public class Minus implements Expr {

    private final int value;

    public Minus(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value * (-1);
    }
}
