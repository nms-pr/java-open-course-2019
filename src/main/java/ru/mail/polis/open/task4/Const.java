package ru.mail.polis.open.task4;

public final class Const implements Expr {

    private final int value;

    public Const(int value) {
        this.value = value;
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
        return this.value == temp.value;
    }



    @Override
    public int evaluate() {
        return value;
    }
}
