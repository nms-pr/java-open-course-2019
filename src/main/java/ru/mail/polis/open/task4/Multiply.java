package ru.mail.polis.open.task4;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Multiply implements Expr {

    private final @NotNull Expr left;
    private final @NotNull Expr right;

    public Multiply(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
