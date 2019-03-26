package ru.mail.polis.open.task4.expressions;

import org.jetbrains.annotations.NotNull;
import ru.mail.polis.open.task4.Expr;

import java.util.Objects;

public class Mult implements Expr{

    private final @NotNull Expr left;
    private final @NotNull Expr right;

    public Mult(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
