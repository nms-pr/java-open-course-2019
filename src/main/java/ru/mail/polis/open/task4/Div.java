package ru.mail.polis.open.task4;

import java.util.Objects;

public final class Div implements Expr {

    private final Expr left;
    private final Expr right;

    public Div(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() / right.evaluate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null
            || getClass() != o.getClass()) {
            return false;
        }
        Div div = (Div) o;
        return hashCode() == div.hashCode();
    }

    @Override
    public int hashCode() {
        return 3 * (left.hashCode() / right.hashCode()) + 1;
    }
}
