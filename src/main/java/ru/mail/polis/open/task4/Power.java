package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Power implements Expr {

    private final @NotNull Expr base;
    private final @NotNull Expr power;

    public Power(final @NotNull Expr base, final @NotNull Expr power) {
        this.base = Objects.requireNonNull(base);
        this.power = Objects.requireNonNull(power);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Power power1 = (Power) o;
        return base.equals(power1.base)
            && power.equals(power1.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, power);
    }

    @Override
    public int evaluate() {
        return Math.round((int) Math.pow(base.evaluate(), power.evaluate()));
    }
}
