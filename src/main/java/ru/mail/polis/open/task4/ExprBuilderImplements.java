package ru.mail.polis.open.task4;

public class ExprBuilderImplements implements ExprBuilder {

    @Override
    public Expr build(String input) {
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Incorrect input expression");
        }

        input.replace(" ", "");


        return null;
    }
}
