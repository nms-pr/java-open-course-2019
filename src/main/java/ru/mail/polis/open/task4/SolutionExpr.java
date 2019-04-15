package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;
import java.util.LinkedList;

public class SolutionExpr implements ExprBuilder {

    private static final char PLUS = '+';
    private static final char SUBSTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DEVIDE = '/';
    private static final char POWER = '^';
    private static final char OPENBRACKET = '(';
    private static final char CLOSEBRACKET = ')';

    private class Result {
        public Expr answer;
        public String raw;

        public Result(Expr answerpart, String r) {
            this.answer = answerpart;
            this.raw = r;
        }
    }

    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input is null!");
        }

        input = input.replaceAll("\\s","");

        if (input.equals("")) {
            throw new IllegalArgumentException("Input is empty!");
        }

        Result result = make(input);
        if (!result.raw.isEmpty()) {
            throw new IllegalArgumentException("Error: can't full parse. rest: " + result.raw);
        }
        return result.answer;
    }

    public boolean isOperation(char symbol) {
        return symbol == PLUS || symbol == SUBSTRACT || symbol == DEVIDE || symbol == POWER || symbol == MULTIPLY;
    }

    public boolean interval(char symbol) {
        return symbol == ' ';
    }

    public int opearatorsPriority(char operand) {
        switch (operand) {
            case PLUS:
            case SUBSTRACT:
                return 1;
            case POWER:
            case DEVIDE:
            case MULTIPLY:
                return 2;
            case OPENBRACKET:
            case CLOSEBRACKET:
                return -1;
            default:
                throw new IllegalArgumentException("Operation not found!");
        }
    }

    public void operator(LinkedList<Integer> number, char operation) {
        int right = number.removeLast();
        int left = number.removeLast();
        switch (operation) {
            case PLUS:
                number.add(new Add(new Const(left),new Const(right)).evaluate());
                break;
            case SUBSTRACT:
                number.add(new Sub(new Const(left),new Const(right)).evaluate());
                break;
            case MULTIPLY:
                number.add(new Mult(new Const(left),new Const(right)).evaluate());
                break;
            case DEVIDE:
                if (new Const(right).evaluate() == 0) {
                    throw new IllegalArgumentException("Divide by zero!");
                } else {
                    number.add(new Div(new Const(left), new Const(right)).evaluate());
                }
                break;
            case POWER:
                number.add(new Pow(new Const(left),new Const(right)).evaluate());
                break;
            default:
                throw new IllegalArgumentException("Operator not found!");
        }
    }


    public Result make(@Nullable String str) {
        SolutionExpr obj = new SolutionExpr();
        LinkedList<Integer> number = new LinkedList<Integer>();
        LinkedList<Character> operation = new LinkedList<Character>();

        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);

            if (obj.interval(symbol)) {
                continue;
            }

            if (symbol == OPENBRACKET) {
                operation.add(OPENBRACKET);
            } else if (symbol == CLOSEBRACKET) {
                while (operation.getLast() != OPENBRACKET) {
                    operator(number, operation.removeLast());
                }
                operation.removeLast();
            } else if (obj.isOperation(symbol)) {
                while (!operation.isEmpty() && obj.opearatorsPriority(operation.getLast())
                        >= obj.opearatorsPriority(symbol)) {
                    operator(number, operation.removeLast());
                }
                operation.add(symbol);
            } else {
                String operand = "";
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    operand += str.charAt(i++);
                }
                --i;
                number.add(new Const(Integer.parseInt(operand)).evaluate());
            }
        }

        while (!operation.isEmpty()) {
            operator(number, operation.removeLast());
        }
        return new Result(new Const(number.get(0)),"");

    }
}
