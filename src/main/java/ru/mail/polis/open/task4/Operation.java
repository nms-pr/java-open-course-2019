package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Operation implements Expr {

    protected final @NotNull Expr left;
    protected final @NotNull Expr right;

    public Operation(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public String toString() {
        return getName() + "(" + left + ", " + right + ")";
    }

    protected abstract String getName();
}

final class Addition extends Operation {

    public Addition(@NotNull Expr left, @NotNull Expr right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }

    @Override
    protected String getName() {
        return " Add";
    }
}

final class Subtraction extends Operation {

    public Subtraction(@NotNull Expr left, @NotNull Expr right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() - right.evaluate();
    }

    @Override
    protected String getName() {
        return " Sub";
    }
}

final class Multiplication extends Operation {

    public Multiplication(@NotNull Expr left, @NotNull Expr right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }

    @Override
    protected String getName() {
        return " Mult";
    }
}

final class Division extends Operation {

    public Division(@NotNull Expr left, @NotNull Expr right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        if (right.evaluate() == 0) {
            throw new ArithmeticException("деление на 0");
        }
        return left.evaluate() / right.evaluate();
    }

    @Override
    protected String getName() {
        return " Div";
    }
}

final class Exponentiation extends Operation {

    public Exponentiation(@NotNull Expr left, @NotNull Expr right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return (int) Math.pow(left.evaluate(), right.evaluate());
    }

    @Override
    protected String getName() {
        return " Pow";
    }
}
