package ru.mail.polis.open.task4;

public class Main {

    public static void main(String[] args) {
        String s = "-9^2";

        ExprBuilderImpl exprBuilder = new ExprBuilderImpl();

        Expr expr;

        try{
            expr = exprBuilder.build(s);
        }catch (Exception e) {
            System.out.println(e);
            return;
        }

        try{
            System.out.println(expr.evaluate());
        }catch (Exception e) {
            System.out.println(e);
            return;
        }

    }
}
