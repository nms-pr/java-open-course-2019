package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Mylt implements Expr {

    private final @NotNull Expr left;
    private final @NotNull Expr right;

    public Mylt(final @NotNull Expr left, final @NotNull Expr right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Mylt mylt = (Mylt) o;
        return mylt.left.equals(this.left) && mylt.right.equals(this.right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
