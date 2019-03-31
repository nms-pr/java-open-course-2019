package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Task!!!
 * Реализовать НЕ обратной польской записью,
 * а Рекурсивным спуском.
 * Суть:
 * каждая операция рассматривается как 'a' 'операция' 'b',
 * причем сначала рассматриваются операции низшего приоритета.
 * (скобки считаются целым операндом)
 * (унарная операция как 0 - a)
 */
public class ExprBuilderImpl implements ExprBuilder {

    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char PLUS = '+';
    private static final char MINUS = '—';
    private static final char UNARY_MINUS = '-';
    private static final char POWER = '^';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static final char SPACE = ' ';

    private Deque<String> stringsDeque = new ArrayDeque<>();

    @Override
    public Expr build(@Nullable String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("нет арифметического выражения");
        }
        input = input.replace(Character.toString(SPACE), "");
        checkForUnknownCharacters(input);
        return recurciveDescent(input);
    }

    private void checkForUnknownCharacters(String expression) {
        if (!expression.matches("[0-9()+—*/^\\-]+")) {
            throw new IllegalArgumentException("в выражении присутствует иной символ");
        }
    }

    private Expr recurciveDescent(@NotNull String input) {
        return addOrSub(input);
    }

    private Expr addOrSub(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == PLUS) {
                addPartInDeque(PLUS, input);
                return new Addition(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
            if (input.charAt(i) == MINUS) {
                addPartInDeque(MINUS, input);
                return new Subtraction(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
        }
        return multOrDiv(input);
    }

    private void addPartInDeque(char operation, String input) {
        String[] leftAndRight;
        if (operation == PLUS || operation == MULTIPLY || operation == POWER) {
            leftAndRight = input.split(String.valueOf("\\" + operation), 2);
        } else {
            leftAndRight = input.split(Character.toString(operation), 2);
            if (operation == UNARY_MINUS) {
                if (!leftAndRight[0].isEmpty() || leftAndRight[1].isEmpty() || !Character.isDigit(leftAndRight[1].charAt(0))){
                    throw new IllegalArgumentException("проблема с унарым минуном");
                }
                leftAndRight[0] = "0"; //for 0 - COUNT
            }
        }

        for (String s : leftAndRight) {
            if (s.isEmpty()) {
                throw new IllegalArgumentException("проблема с частью выражения");
            }
        }

        stringsDeque.addFirst(leftAndRight[1]);
        stringsDeque.addFirst(leftAndRight[0]);
    }

    private Expr multOrDiv(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == MULTIPLY) {
                addPartInDeque(MULTIPLY, input);
                return new Multiplication(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
            if (input.charAt(i) == DIVIDE) {
                addPartInDeque(DIVIDE, input);
                return new Division(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
        }
        return pow(input);
    }

    private Expr pow(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == POWER) {
                addPartInDeque(POWER, input);
                return new Exponentiation(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
        }
        return unaryMinus(input);
    }

    private Expr unaryMinus(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == UNARY_MINUS) {
                addPartInDeque(UNARY_MINUS, input);
                return new Subtraction(recurciveDescent(stringsDeque.pop()), recurciveDescent(stringsDeque.pop()));
            }
        }
        return getConst(input);
    }

    private Expr getConst(String input) {


        return new Const(Integer.valueOf(input));
    }
}
