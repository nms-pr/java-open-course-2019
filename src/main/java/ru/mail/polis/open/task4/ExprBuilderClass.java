package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class ExprBuilderClass implements ExprBuilder {
    private static final char MULT = '*';
    private static final char DIV = '/';
    private static final char PLUS = '+';
    private static final char MINUS = '—';
    private static final char POW = '^';
    private static final char OPENING_BRACKET = '(';
    private static final char CLOSING_BRACKET = ')';
    private static final char UNARY_MINUS = '-';
    private static int bracketCount = 0;

    public Expr build(@Nullable String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        //remove first and last brackets
        String newInput = input.replaceAll(" ", "");
        if (newInput.charAt(0) == OPENING_BRACKET && newInput.charAt(newInput.length() - 1) == CLOSING_BRACKET) {
            for (int i = 0; i < newInput.length(); i++) {
                if (newInput.charAt(i) == OPENING_BRACKET) {
                    bracketCount++;
                } else if (newInput.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
                if (bracketCount == 0) {
                    if (i == newInput.length() - 1) {
                        newInput = newInput.substring(1, newInput.length() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        //plus and minus
        for (int i = 0; i < newInput.length(); i++) {
            if (newInput.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (newInput.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (newInput.charAt(i) == PLUS) {
                    return new Add(build(newInput.substring(0, i)), build(newInput.substring(i + 1)));
                }
                if (input.charAt(i) == MINUS) {
                    return new Sub(build(newInput.substring(0, i)), build(newInput.substring(i + 1)));
                }
            }
        }
        if (bracketCount != 0) {
            bracketCount = 0;
            throw new IllegalArgumentException();
        }
        //mult and div
        for (int i = 0; i < newInput.length(); i++) {
            if (newInput.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (newInput.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (newInput.charAt(i) == MULT) {
                    return new Mult(build(newInput.substring(0, i)), build(newInput.substring(i + 1)));
                }
                if (input.charAt(i) == DIV) {
                    return new Div(build(newInput.substring(0, i)), build(newInput.substring(i + 1)));
                }
            }
        }
        //power
        for (int i = 0; i < newInput.length(); i++) {
            if (newInput.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (newInput.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (newInput.charAt(i) == POW) {
                    return new Pow(build(newInput.substring(0, i)), build(newInput.substring(i + 1)));
                }
            }
        }
        //unary minus and constants
        if (newInput.charAt(0) == UNARY_MINUS) {
            return new UnaryMinus(Integer.parseInt(newInput.substring(1)));
        }
        for (int i = 0; i < newInput.length(); i++) {
            if (!Character.isDigit(newInput.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        return new Const(Integer.parseInt(newInput));
    }
}
