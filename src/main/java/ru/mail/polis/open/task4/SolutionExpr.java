package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

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
            throw new IllegalArgumentException("Imput is null!");
        }

        input = input.replaceAll("\\s","");

        if (input.equals("")) {
            throw new IllegalArgumentException("Input is empty!");
        }

        Result result = plusMinus(input);
        if (!result.raw.isEmpty()) {
            throw new IllegalArgumentException("Error: can't full parse. rest: " + result.raw);
        }
        return result.answer;
    }

    private Result plusMinus(String s) {
        Result current = mulDiv(s);
        Expr result = current.answer;

        while (current.raw.length() > 0) {
            if (!(current.raw.charAt(0) == PLUS || current.raw.charAt(0) == SUBSTRACT)) {
                break;
            }

            char sign = current.raw.charAt(0);
            String next = current.raw.substring(1);

            current = mulDiv(next);
            if (sign == PLUS) {
                result = new Add(result, current.answer);
            } else {
                result = new Sub(result, current.answer);
            }
        }
        return new Result(result, current.raw);
    }

    private Result mulDiv(String s) {
        Result current = pow(s);

        Expr result = current.answer;
        while (true) {
            if (current.raw.length() == 0) {
                return current;
            }
            char sign = current.raw.charAt(0);
            if ((sign != MULTIPLY && sign != DEVIDE)) {
                return current;
            }

            String next = current.raw.substring(1);
            Result right = pow(next);

            if (sign == '*') {
                result = new Mult(result, right.answer);
            } else if (right.answer.evaluate() == 0) {
                throw new IllegalArgumentException("Dividing by 0!");
            } else {
                result = new Div(result, right.answer);
            }

            current = new Result(result, right.raw);
        }
    }

    private Result pow(String s) {
        Result current = bracket(s);
        Expr result = current.answer;

        while (true) {
            if (current.raw.length() == 0) {
                return current;
            }
            char sign = current.raw.charAt(0);
            if (sign != POWER) {
                return current;
            }

            String next = current.raw.substring(1);
            Result right = bracket(next);

            result = new Pow(result, right.answer);

            current = new Result(result, right.raw);
        }
    }

    private Result bracket(String s) {
        char zeroChar = s.charAt(0);
        if (zeroChar == OPENBRACKET) {
            Result r = plusMinus(s.substring(1));
            if (!r.raw.isEmpty() && r.raw.charAt(0) == CLOSEBRACKET) {
                r.raw = r.raw.substring(1);
            } else {
                throw new IllegalArgumentException("CLOSEBRACKET not found!");
            }
            return r;
        }
        return num(s);
    }

    private Result num(String line) {
        int i = 0;
        int exprPart;
        boolean minus = false;
        if (line.charAt(0) == SUBSTRACT) {
            minus = true;
            line = line.substring(1);
        }
        while (i < line.length() && Character.isDigit(line.charAt(i))) {
            i++;
        }

        if ((line.charAt(0) == OPENBRACKET || line.charAt(0) == '-') && minus) {
            Result r = plusMinus(line);
            exprPart = -r.answer.evaluate();
            return new Result(new Const(exprPart), r.raw);
        }

        if (i == 0) {
            throw new IllegalArgumentException("Can't get valid number in '" + line + "'!");
        }

        exprPart = Integer.parseInt(line.substring(0, i));
        if (minus) {
            exprPart = -exprPart;
        }
        String restPart = line.substring(i);

        return new Result(new Const(exprPart), restPart);
    }

}
