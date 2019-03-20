package ru.mail.polis.open.task4;

public final class UnMin implements Expr {

    private final Expr opperand;

    public UnMin(Expr opperand) {
        this.opperand = opperand;
    }

    @Override
    public int evaluate() {
        return - opperand.evaluate();
    }
}
