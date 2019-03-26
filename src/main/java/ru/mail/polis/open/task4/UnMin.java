package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class UnMin implements Expr {

    private final @NotNull Expr operand;

    public UnMin(final @NotNull Expr operand) {
        this.operand = Objects.requireNonNull(operand);
    }

    @Override
    public int evaluate() {
        return - operand.evaluate();
    }
}
