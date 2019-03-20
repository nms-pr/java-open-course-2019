package ru.mail.polis.open.task4;

import java.util.Objects;

public final class UnMin implements Expr {

    private final Expr opperand;

    public UnMin(Expr opperand) {
        this.opperand = opperand;
    }

    @Override
    public int evaluate() {
        return - opperand.evaluate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnMin unMin = (UnMin) o;
        return Objects.equals(opperand, unMin.opperand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opperand);
    }
}
