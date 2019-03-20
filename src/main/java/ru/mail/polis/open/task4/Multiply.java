package ru.mail.polis.open.task4;

import java.util.Objects;

public class Multiply implements Expr {

    private final Expr left;
    private final Expr right;

    public Multiply(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Multiply multiply = (Multiply) o;
        return left.equals(multiply.left) &&
                right.equals(multiply.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
