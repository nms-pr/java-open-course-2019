package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class Parser implements ExprBuilder {

    private class Result {
        public Expr acc; // Аккумулятор
        public String rest; // остаток строки, которую мы еще не обработали

        public Result(Expr v, String r) {
            this.acc = v;
            this.rest = r;
        }
    }

    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null string");
        }

        input = input.replaceAll("\\s","");

        if (input.equals("")) {
            throw new IllegalArgumentException("Empty string");
        }

        Result result = plusMinus(input);
        if (!result.rest.isEmpty()) {
            throw new IllegalArgumentException("Error: can't full parse. rest: " + result.rest);
        }
        return result.acc;
    }

    private Result plusMinus(String s) {
        Result current = mulDiv(s);
        Expr acc = current.acc;

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) {
                break;
            }

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = mulDiv(next);
            if (sign == '+') {
                acc = new Add(acc, current.acc);
            } else {
                acc = new Substract(acc, current.acc);
            }
        }
        return new Result(acc, current.rest);
    }

    private Result mulDiv(String s) {
        Result current = pow(s);

        Expr acc = current.acc;
        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) {
                return current;
            }

            String next = current.rest.substring(1);
            Result right = pow(next);

            if (sign == '*') {
                acc = new Multiply(acc, right.acc);
            } else if (right.acc.evaluate() == 0) {
                throw new IllegalArgumentException("Cannot divide by zero");
            } else {
                acc = new Divide(acc, right.acc);
            }

            current = new Result(acc, right.rest);
        }
    }

    private Result pow(String s) {
        Result current = bracket(s);
        Expr acc = current.acc;

        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if (sign != '^') {
                return current;
            }

            String next = current.rest.substring(1);
            Result right = bracket(next);

            acc = new Power(acc, right.acc);

            current = new Result(acc, right.rest);
        }
    }

    private Result bracket(String s) {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            Result r = plusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                throw new IllegalArgumentException("Error: no close bracket");
            }
            return r;
        }
        return num(s);
    }

    private Result num(String s) {
        int i = 0;
        int digitPart;
        boolean negative = false;
        // число также может начинаться с минуса
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        }
        // разрешаем только цифры
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            i++;
        }

        if ((s.charAt(0) == '(' || s.charAt(0) == '-') && negative) {
            Result r = plusMinus(s);
            digitPart = -r.acc.evaluate();
            return new Result(new Const(digitPart), r.rest);
        }

        if (i == 0) { // похожее на число мы не нашли
            throw new IllegalArgumentException("can't get valid number in '" + s + "'");
        }

        digitPart = Integer.parseInt(s.substring(0, i));
        if (negative) {
            digitPart = -digitPart;
        }
        String restPart = s.substring(i);

        return new Result(new Const(digitPart), restPart);
    }

}
