package ru.mail.polis.open.task4;

import java.util.Objects;

public final class Mult implements Expr {

    private final Expr left;
    private final Expr right;

    public Mult(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mult mult = (Mult) o;
        return hashCode() == mult.hashCode();
    }

    @Override
    public int hashCode() {
        return ((left.hashCode() * right.hashCode()) / 3 - 3);
    }
}
