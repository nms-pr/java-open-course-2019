package ru.mail.polis.open.task4;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pow pow = (Pow) o;
        return hashCode() == pow.hashCode();
    }

    @Override
    public int hashCode() {
        return (int)(Math.pow(left.hashCode(), right.hashCode()) / 10) + 3;
    }
}
