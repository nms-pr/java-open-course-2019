package ru.mail.polis.open.task4;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class UnaryMinus implements Expr {

    private final @NotNull Expr expr;

    public UnaryMinus(final @NotNull Expr expr) {
        this.expr = Objects.requireNonNull(expr);
    }

    @Override
    public int evaluate() {
        return -expr.evaluate();
    }
}
