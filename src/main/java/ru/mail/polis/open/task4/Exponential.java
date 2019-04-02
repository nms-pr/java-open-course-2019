package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Exponential implements Expr {

    private final @NotNull Expr base;
    private final @NotNull Expr power;

    public Exponential(final @NotNull Expr base, final @NotNull Expr power) {
        this.base = Objects.requireNonNull(base);
        this.power = Objects.requireNonNull(power);
    }

    @Override
    public int evaluate() {
        return Math.round((int) Math.pow(base.evaluate(), power.evaluate()));
    }
}
