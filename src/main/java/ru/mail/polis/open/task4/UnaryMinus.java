package ru.mail.polis.open.task4;

public class UnaryMinus implements Expr {

    private final Expr expr;

    public UnaryMinus(Expr expr) {
        this.expr = expr;
    }

    @Override
    public int evaluate() {
        return -expr.evaluate();
    }
}
