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
        String newInput = input.replaceAll(" ", "");
        return brackets(newInput);
    }

    private Expr brackets(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (input.charAt(0) == OPENING_BRACKET && input.charAt(input.length() - 1) == CLOSING_BRACKET) {
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == OPENING_BRACKET) {
                    bracketCount++;
                } else if (input.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
                if (bracketCount == 0) {
                    if (i == input.length() - 1) {
                        input = input.substring(1, input.length() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        return plusMinus(input);
    }

    private Expr plusMinus(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (input.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (input.charAt(i) == PLUS) {
                    return new Add(brackets(input.substring(0, i)), brackets(input.substring(i + 1)));
                }
                if (input.charAt(i) == MINUS) {
                    return new Sub(brackets(input.substring(0, i)), brackets(input.substring(i + 1)));
                }
            }
        }
        if (bracketCount != 0) {
            bracketCount = 0;
            throw new IllegalArgumentException();
        }
        return multDiv(input);
    }

    private Expr multDiv(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (input.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (input.charAt(i) == MULT) {
                    return new Mult(brackets(input.substring(0, i)), brackets(input.substring(i + 1)));
                }
                if (input.charAt(i) == DIV) {
                    return new Div(brackets(input.substring(0, i)), brackets(input.substring(i + 1)));
                }
            }
        }
        return power(input);
    }

    private Expr power(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPENING_BRACKET) {
                bracketCount++;
            } else {
                if (input.charAt(i) == CLOSING_BRACKET) {
                    bracketCount--;
                }
            }
            if (bracketCount == 0) {
                if (input.charAt(i) == POW) {
                    return new Pow(brackets(input.substring(0, i)), brackets(input.substring(i + 1)));
                }
            }
        }
        return unaryMinusConstant(input);
    }

    private Expr unaryMinusConstant(String input) {
        if (input.charAt(0) == UNARY_MINUS) {
            return new UnaryMinus(Integer.parseInt(input.substring(1)));
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                throw new IllegalArgumentException();
            }
        }
        return new Const(Integer.parseInt(input));
    }
}
