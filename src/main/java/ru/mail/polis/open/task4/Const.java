package ru.mail.polis.open.task4;

public final class Const implements Expr {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
