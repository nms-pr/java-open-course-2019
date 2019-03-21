package ru.mail.polis.open.task4;

import java.util.Objects;

public class Divide implements Expr {

    private final Expr divident;
    private final Expr divider;

    public Divide(Expr divident, Expr divider) {
        this.divident = divident;
        this.divider = divider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Divide divide = (Divide) o;
        return divident.equals(divide.divident)
            && divider.equals(divide.divider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(divident, divider);
    }

    @Override
    public int evaluate() {
        return divident.evaluate() / divider.evaluate();
    }
}
