package ru.mail.polis.open.task4;

public class UnaryMinus implements Expr {

    private final int value;

    public UnaryMinus(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return -value;
    }
}
