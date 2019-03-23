package ru.mail.polis.open.task4;

import java.util.Objects;

public final class Const implements Expr {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Const temp = (Const) o;
        return hashCode() == temp.hashCode();
    }

    @Override
    public int hashCode() {
        return (value * 3 - 8) * 2 ;
    }
}
