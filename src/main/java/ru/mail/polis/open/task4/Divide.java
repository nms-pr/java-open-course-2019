package ru.mail.polis.open.task4;

public class Divide implements Expr {

    private final Expr divident;
    private final Expr divider;

    public Divide(Expr divident, Expr divider) {
        this.divident = divident;
        this.divider = divider;
    }

    @Override
    public int evaluate() {
        return divident.evaluate() / divider.evaluate();
    }
}
