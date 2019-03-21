package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UnaryMinus implements Expr {

    private final @NotNull Expr expr;

    public UnaryMinus(final @NotNull Expr expr) {
        this.expr = Objects.requireNonNull(expr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnaryMinus that = (UnaryMinus) o;
        return expr.equals(that.expr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }

    @Override
    public int evaluate() {
        return -expr.evaluate();
    }
}
