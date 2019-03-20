package ru.mail.polis.open.task4;

public class Power implements Expr {

    private final Expr base;
    private final Expr power;

    public Power(Expr base, Expr power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public int evaluate() {
        return Math.round((int) Math.pow(base.evaluate(), power.evaluate()));
    }
}
