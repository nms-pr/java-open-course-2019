package ru.mail.polis.open.task4;

public final class Pow implements Expr {

    private final Expr left;
    private final Expr right;

    public Pow(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return (int)(Math.pow(left.evaluate(), right.evaluate()));
    }
}
