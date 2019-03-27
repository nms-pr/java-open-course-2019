package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Brackets implements Expr {

    private final @NotNull Expr value;

    public Brackets(final @NotNull Expr value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public int evaluate() {
        return value.evaluate();
    }
}