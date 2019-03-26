package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UnaryMinus implements Expr {

    private final @NotNull Expr right;

    public UnaryMinus(final @NotNull Expr right) {
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public int evaluate() {
        return - right.evaluate();
    }
}
