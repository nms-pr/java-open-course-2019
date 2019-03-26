package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class UnarMinus implements Expr {

    private final @NotNull Expr number;

    public UnarMinus(final @NotNull Expr number) {
        this.number = Objects.requireNonNull(number);
    }

    @Override
    public int evaluate() {

        return (-1 * number.evaluate());
    }
}
