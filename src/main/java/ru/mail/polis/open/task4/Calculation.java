package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculation implements ExprBuilder {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '—';
    private static final char UNARY_MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char POWER = '^';
    private static int bracketCounter = 0;
    private static Deque<String> stack = new ArrayDeque<>();

    @Override
    public Expr build(@Nullable String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        input = input.replace(" ", "");
        return parse(input);
    }

    Expr parse(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (input.charAt(0) == OPEN_BRACKET && input.charAt(input.length() - 1) == CLOSE_BRACKET) {
            bracketCounter = 1;
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == OPEN_BRACKET) {
                    bracketCounter++;
                }
                if (input.charAt(i) == CLOSE_BRACKET) {
                    bracketCounter--;
                }
                if (bracketCounter == 0 && i != input.length() - 1) {
                    break;
                }
                if (bracketCounter == 0 && i == input.length() - 1) {
                    input = input.substring(1, input.length() - 1);
                    break;
                }

            }
        }
        return plusMinus(input);
    }


    Expr plusMinus(String input) {

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == OPEN_BRACKET) {
                bracketCounter++;
            }
            if (input.charAt(i) == CLOSE_BRACKET) {
                bracketCounter--;
            }
            if (bracketCounter == 0) {
                if (input.charAt(i) == PLUS) {
                    stack.push(input.substring(i + 1));
                    stack.push(input.substring(0, i));
                    return new Add(parse(stack.pop()), parse(stack.pop()));
                }
                if (input.charAt(i) == MINUS) {
                    stack.push(input.substring(i + 1));
                    stack.push(input.substring(0, i));
                    return new Subtraction(parse(stack.pop()), parse(stack.pop()));
                }
            }
        }
        if (!(bracketCounter == 0)) {
            bracketCounter = 0;
            throw new IllegalArgumentException();
        }
        return multDivide(input);
    }

    Expr multDivide(String input) {

        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == OPEN_BRACKET) {
                bracketCounter++;
            }
            if (input.charAt(i) == CLOSE_BRACKET) {
                bracketCounter--;
            }
            if (bracketCounter == 0) {
                if (input.charAt(i) == MULTIPLY) {
                    stack.push(input.substring(i + 1));
                    stack.push(input.substring(0, i));
                    return new Multiplication(parse(stack.pop()), parse(stack.pop()));
                }
                if (input.charAt(i) == DIVIDE) {
                    stack.push(input.substring(i + 1));
                    stack.push(input.substring(0, i));
                    return new Division(parse(stack.pop()), parse(stack.pop()));
                }
            }
        }
        return power(input);
    }

    Expr power(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == POWER) {
                stack.push(input.substring(i + 1));
                stack.push(input.substring(0, i));
                return new Power(parse(stack.pop()), parse(stack.pop()));
            }
        }

        return constant(input);
    }

    Expr constant(String input) {
        if (input.charAt(0) == UNARY_MINUS) {
            input = input.substring(1);
            return unaryMinus(input);
        }
        if (!(input.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException();
        }
        return new Const(Integer.parseInt(input));
    }

    Expr unaryMinus(String input) {
        if (!(input.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException();
        }
        return new UnaryMinus(new Const(Integer.parseInt(input)));
    }

}
