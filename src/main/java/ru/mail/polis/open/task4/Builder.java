package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class Builder implements ExprBuilder {
    /*
    * Парсер математических выражений.
    * Суть данного парсера состоит в том, что он разделяется на свои подзадачи
    * В свою очередь подзадача должна работать только с тем, с чем умеет работать
    * если условия не удовлетворяются передавать управление дальше
    * Если же условия удовлетворены, делаем вычисления и передаем оставшуюся часть необработанного текста
    * Выполнение происходит до тех пор, пока текст все еще есть или же
    * если ни одна подзадача не может обработать текущее состояние.
    */
    private static final char ADD_OPERAND = '+';
    private static final char SUBSTRACT_OPERAND = '-';
    private static final char MULTIPLY_OPERAND = '*';
    private static final char DIVIDE_OPERAND = '/';
    private static final char POWER_OPERAND = '^';
    private static final char OPENING_BRACKET = '(';
    private static final char CLOSING_BRACKET = ')';

    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException("Error: Null string");
        }

        input = input.replaceAll(" ","");

        if (input.equals("")) {
            throw new IllegalArgumentException("Error: Empty string");
        }

        //отправляем строку парситься
        Pair result = plusMinus(input);
        if (!result.restLine.isEmpty()) {
            throw new IllegalArgumentException("Error: can't full parse. restLine: " + result.restLine);
        }
        return result.accumulator;
    }

    private Pair plusMinus(String s) {
        Pair current = mulDiv(s);
        Expr accumulator = current.accumulator;

        while (current.restLine.length() > 0) {
            if (!(current.restLine.charAt(0) == ADD_OPERAND || current.restLine.charAt(0) == SUBSTRACT_OPERAND)) {
                break;
            }

            char sign = current.restLine.charAt(0);
            String next = current.restLine.substring(1);

            current = mulDiv(next);
            if (sign == ADD_OPERAND) {
                accumulator = new Add(accumulator, current.accumulator);
            } else {
                accumulator = new Substract(accumulator, current.accumulator);
            }
        }
        return new Pair(accumulator, current.restLine);
    }

    private Pair mulDiv(String s) {
        Pair current = pow(s);

        Expr accumulator = current.accumulator;
        while (true) {
            if (current.restLine.length() == 0) {
                return current;
            }
            char sign = current.restLine.charAt(0);
            if ((sign != MULTIPLY_OPERAND && sign != DIVIDE_OPERAND)) {
                return current;
            }

            String next = current.restLine.substring(1);
            Pair right = pow(next);

            if (sign == MULTIPLY_OPERAND) {
                accumulator = new Multiply(accumulator, right.accumulator);
            } else if (right.accumulator.evaluate() == 0) {
                throw new IllegalArgumentException("Error: Cannot divide by zero");
            } else {
                accumulator = new Divide(accumulator, right.accumulator);
            }

            current = new Pair(accumulator, right.restLine);
        }
    }

    private Pair pow(String s) {
        Pair current = bracket(s);
        Expr accumulator = current.accumulator;

        while (true) {
            if (current.restLine.length() == 0) {
                return current;
            }
            char sign = current.restLine.charAt(0);
            if (sign != POWER_OPERAND) {
                return current;
            }

            String next = current.restLine.substring(1);
            Pair right = bracket(next);

            accumulator = new Power(accumulator, right.accumulator);

            current = new Pair(accumulator, right.restLine);
        }
    }

    private Pair bracket(String s) {
        char zeroChar = s.charAt(0);
        if (zeroChar == OPENING_BRACKET) {
            Pair r = plusMinus(s.substring(1));
            if (!r.restLine.isEmpty() && r.restLine.charAt(0) == CLOSING_BRACKET) {
                r.restLine = r.restLine.substring(1);
            } else {
                throw new IllegalArgumentException("Error: no close bracket");
            }
            return r;
        }
        return num(s);
    }

    private Pair num(String s) {
        int i = 0;
        int digitPart;
        boolean negative = false;
        // число также может начинаться с минуса
        if (s.charAt(0) == SUBSTRACT_OPERAND) {
            negative = true;
            s = s.substring(1);
        }
        // разрешаем только цифры
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            i++;
        }

        if ((s.charAt(0) == OPENING_BRACKET || s.charAt(0) == SUBSTRACT_OPERAND) && negative) {
            Pair r = plusMinus(s);
            digitPart = -r.accumulator.evaluate();
            return new Pair(new Const(digitPart), r.restLine);
        }

        if (i == 0) { // похожее на число мы не нашли
            throw new IllegalArgumentException("Error: can't get valid number in '" + s + "'");
        }

        digitPart = Integer.parseInt(s.substring(0, i));
        if (negative) {
            digitPart = -digitPart;
        }
        String restLinePart = s.substring(i);

        return new Pair(new Const(digitPart), restLinePart);
    }

    private class Pair {
        public Expr accumulator; // Аккумулятор
        public String restLine; // остаток строки, которую мы еще не обработали

        public Pair(Expr v, String r) {
            this.accumulator = v;
            this.restLine = r;
        }
    }
}
