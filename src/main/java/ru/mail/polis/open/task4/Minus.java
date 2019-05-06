package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Minus implements Expr {

    private final @NotNull Expr value;

    public Minus(final @NotNull Expr value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public int evaluate() {
        return - value.evaluate();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Minus that = (Minus) o;

        return Objects.equals(value, that.value);
    }

}
