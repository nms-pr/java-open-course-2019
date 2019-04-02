package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Divide implements Expr {

    private final @NotNull Expr divident;
    private final @NotNull Expr divider;

    public Divide(final @NotNull Expr divident, final @NotNull Expr divider) {
        this.divident = Objects.requireNonNull(divident);
        this.divider = Objects.requireNonNull(divider);
    }

    @Override
    public int evaluate() {
        return divident.evaluate() / divider.evaluate();
    }

}
