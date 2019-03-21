package ru.mail.polis.open.task4;

import java.util.Objects;

public final class Sub implements Expr {

    private final Expr left;
    private final Expr right;

    public Sub(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() - right.evaluate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sub sub = (Sub) o;
        return Objects.equals(left, sub.left)
            && Objects.equals(right, sub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
