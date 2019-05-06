package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Multiplication implements Expr {
    private final @NotNull Expr left;
    private final @NotNull Expr right;

    public Multiplication(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
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
        Multiplication that = (Multiplication) o;
        return Objects.equals(left, that.left)
                && Objects.equals(right, that.right);
    }

}