package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Builder implements ExprBuilder {

    private static Cache cache;

    static {
        cache = new Cache(20);
    }

    private static final char[] OPERANDS = {'+', '-', '*', '/', '^'};

    private boolean bracketsCheckedAndCorrect = false;

    @Override
    public Expr build(@Nullable String input) {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Input is incorrect");
        if (input == null) {
            throw illegalArgumentException;
        }
        String spacelessInput = input.replaceAll(" ", "");
        try {
            return cache.getExpression(spacelessInput);
        } catch (NullPointerException ignored) {
            int a = 0;
        }
        if (spacelessInput.startsWith("-")) {
            spacelessInput = spacelessInput.replaceFirst("-", "0-");
        }
        if (spacelessInput.matches("\\(*-?\\d+\\)*")) {
            return new Const(Integer.parseInt(spacelessInput.replaceAll("[)(]", "")));
        }
        if (!spacelessInput.matches("[\\d-—*/+^)(]+")) {
            throw illegalArgumentException;
        }
        Map<Integer, Integer> bracketsPlaces = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int lastClosingBracket = -2;
        for (int i = 0; i < spacelessInput.length(); i++) {
            if (spacelessInput.charAt(i) == '(') {
                if (i - lastClosingBracket < 2) {
                    throw illegalArgumentException;
                }
                stack.push(i);
            }
            if (spacelessInput.charAt(i) == ')') {
                lastClosingBracket = i;
                try {
                    int start = stack.peek();
                    if (i - start < 3
                            || spacelessInput.substring(start + 1, i).replaceAll("\\D", "").isEmpty()) {
                        throw illegalArgumentException;
                    }
                    bracketsPlaces.put(stack.pop(), i);
                } catch (EmptyStackException e) {
                    if (!bracketsCheckedAndCorrect) {
                        throw illegalArgumentException;
                    }
                }
            }
        }
        if (!stack.isEmpty() && !bracketsCheckedAndCorrect) {
            throw illegalArgumentException;
        }
        bracketsCheckedAndCorrect = true;
        if (bracketsPlaces.containsKey(0) && bracketsPlaces.get(0) == spacelessInput.length() - 1) {
            return build(spacelessInput.substring(1, spacelessInput.length() - 1));
        }
        int index = 0;
        int step;
        boolean leftToRight;
        int openingBracketsPassed;
        int closingBracketsPassed;
        for (char operand: OPERANDS) {
            openingBracketsPassed = 0;
            closingBracketsPassed = 0;
            if (operand != '-' && operand != '/') {
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
                if (spacelessInput.charAt(index) == ')') {
                    closingBracketsPassed++;
                }
                if (spacelessInput.charAt(index) == '(') {
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
        switch (spacelessInput.charAt(index)) {
            case '+': {
                expr = new Add(build(spacelessInput.substring(0, index)),
                        build(spacelessInput.substring(index + 1, spacelessInput.length())));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case '-': {
                expr = new Substract(build(spacelessInput.substring(0, index)),
                        build(spacelessInput.substring(index + 1, spacelessInput.length())));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case '/': {
                expr = new Divide(build(spacelessInput.substring(0, index)),
                        build(spacelessInput.substring(index + 1, spacelessInput.length())));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case '*': {
                expr = new Multiply(build(spacelessInput.substring(0, index)),
                        build(spacelessInput.substring(index + 1, spacelessInput.length())));
                cache.put(spacelessInput, expr);
                return expr;
            }
            case '^': {
                expr = new Power(build(spacelessInput.substring(0, index)),
                        build(spacelessInput.substring(index + 1, spacelessInput.length())));
                cache.put(spacelessInput, expr);
                return expr;
            }
            default: {
                return null;
            }
        }
    }
}
