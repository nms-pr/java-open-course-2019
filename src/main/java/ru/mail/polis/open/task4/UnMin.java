package ru.mail.polis.open.task4;

import java.util.Objects;

public final class UnMin implements Expr {

    private final Expr operand;

    public UnMin(Expr operand) {
        this.operand = operand;
    }

    @Override
    public int evaluate() {
        return - operand.evaluate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnMin unMin = (UnMin) o;
        return hashCode() == unMin.hashCode();
    }

    @Override
    public int hashCode() {
        return operand.hashCode() * 3 - 23;
    }
}
