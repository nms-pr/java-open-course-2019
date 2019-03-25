package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

import java.util.EmptyStackException;
import java.util.Stack;

public class Builder implements ExprBuilder {

    private static Cache cache;

    static {
        cache = new Cache(20);
    }

    private static final char ADD_OPERAND = '+';
    private static final char SUBSTR_OPERAND = '-';
    private static final char MULT_OPERAND = '*';
    private static final char DIV_OPERAND = '/';
    private static final char POW_OPERAND = '^';
    private static final char OPENING_BRACKET = '(';
    private static final char CLOSING_BRACKET = ')';
    private static final char[] OPERANDS = {ADD_OPERAND, SUBSTR_OPERAND, MULT_OPERAND, DIV_OPERAND, POW_OPERAND};
    private static final String ERROR_MESSAGE = "Input is incorrect";
    private static final int MIN_DISTANCE_BETWEEN_BRACKETS = 3;
    private static final int MIN_DISTANCE_BETWEEN_BRACKET_EXPR = 2;
    private static final int LAST_CLOSING_BRACKET_START_POS = -2;
    private static final String LEADING_MINUS_REPLACEMENT = "0" + String.valueOf(SUBSTR_OPERAND);
    private static final String CONST_REGEX = String.valueOf(SUBSTR_OPERAND) + "?\\d+";
    private static final String BRACKETS_REGEX = "["
            + String.valueOf(CLOSING_BRACKET)
            + String.valueOf(OPENING_BRACKET)
            + "]";
    private static final String CORRECT_INPUT_REGEX = "[\\d"
            + String.valueOf(SUBSTR_OPERAND)
            + String.valueOf(MULT_OPERAND)
            + String.valueOf(DIV_OPERAND)
            + String.valueOf(ADD_OPERAND)
            + String.valueOf(POW_OPERAND)
            + String.valueOf(CLOSING_BRACKET)
            + String.valueOf(OPENING_BRACKET)
            + "]+";

    private boolean bracketsCheckedAndCorrect = false;
    private boolean firstIteration = true;

    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        String spacelessInput = input.replaceAll(" ", "");
        try {
            return cache.getExpression(spacelessInput);
        } catch (NullPointerException ignored) {
            // Such expression never appeared before, so it's not cached. Just continue building.
        }
        if (spacelessInput.startsWith(String.valueOf(SUBSTR_OPERAND))) {
            spacelessInput = spacelessInput.replaceFirst(String.valueOf(SUBSTR_OPERAND), LEADING_MINUS_REPLACEMENT);
        }
        if (spacelessInput.matches(CONST_REGEX)) {
            return new Const(Integer.parseInt(spacelessInput.replaceAll(BRACKETS_REGEX, "")));
        }
        if (!spacelessInput.matches(CORRECT_INPUT_REGEX)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        boolean wholeExpressionInBrackets = false;
        Stack<Integer> stack = new Stack<>();
        int lastClosingBracket = LAST_CLOSING_BRACKET_START_POS;
        for (int i = 0; i < spacelessInput.length(); i++) {
            if (spacelessInput.charAt(i) == OPENING_BRACKET) {
                if (i - lastClosingBracket < MIN_DISTANCE_BETWEEN_BRACKET_EXPR) {
                    throw new IllegalArgumentException(ERROR_MESSAGE);
                }
                stack.push(i);
            }
            if (spacelessInput.charAt(i) == CLOSING_BRACKET) {
                lastClosingBracket = i;
                try {
                    int start = stack.peek();
                    if (i - start < MIN_DISTANCE_BETWEEN_BRACKETS
                            || spacelessInput.substring(start + 1, i).replaceAll("\\D", "").isEmpty()) {
                        throw new IllegalArgumentException(ERROR_MESSAGE);
                    }
                    if (i == spacelessInput.length() - 1 && start == 0) {
                        wholeExpressionInBrackets = true;
                    }
                    stack.pop();
                } catch (EmptyStackException e) {
                    if (!bracketsCheckedAndCorrect) {
                        throw new IllegalArgumentException(ERROR_MESSAGE);
                    }
                }
            }
        }
        if (!stack.isEmpty() && !bracketsCheckedAndCorrect) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        bracketsCheckedAndCorrect = true;
        if (wholeExpressionInBrackets) {
            if (firstIteration) {
                throw new IllegalArgumentException(ERROR_MESSAGE);
            }
            return build(spacelessInput.substring(1, spacelessInput.length() - 1));
        }
        firstIteration = false;
        int index = 0;
        int step;
        boolean leftToRight;
        int openingBracketsPassed;
        int closingBracketsPassed;
        for (char operand: OPERANDS) {
            openingBracketsPassed = 0;
            closingBracketsPassed = 0;
            if (operand != SUBSTR_OPERAND && operand != DIV_OPERAND) {
                index = 0;
                step = 1;
                leftToRight = true;
            } else {
                index = spacelessInput.length() - 1;
                step = -1;
                leftToRight = false;
            }
            while ((leftToRight && index < spacelessInput.length() || !leftToRight && index >= 0)
                    && spacelessInput.charAt(index) != operand
                    || openingBracketsPassed != closingBracketsPassed) {
                if (spacelessInput.charAt(index) == CLOSING_BRACKET) {
                    closingBracketsPassed++;
                }
                if (spacelessInput.charAt(index) == OPENING_BRACKET) {
                    openingBracketsPassed++;
                }
                index += step;
            }
            if (index != spacelessInput.length() && leftToRight
                    || index != -1 && !leftToRight) {
                break;
            }
        }
        Expr expr;
        String leftSubstring = spacelessInput.substring(0, index);
        String rightSubstring = spacelessInput.substring(index + 1, spacelessInput.length());
        switch (spacelessInput.charAt(index)) {
            case ADD_OPERAND: {
                expr = new Add(build(leftSubstring), build(rightSubstring));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case SUBSTR_OPERAND: {
                expr = new Substract(build(leftSubstring), build(rightSubstring));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case DIV_OPERAND: {
                expr = new Divide(build(leftSubstring), build(rightSubstring));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case MULT_OPERAND: {
                expr = new Multiply(build(leftSubstring), build(rightSubstring));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case POW_OPERAND: {
                expr = new Power(build(leftSubstring), build(rightSubstring));
                cache.put(spacelessInput, expr);
                return expr;
            }
            default: {
                return null;
            }
        }
    }
}
