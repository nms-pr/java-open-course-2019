package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.Nullable;

public class RecursuveDescentExprBuilder implements ExprBuilder {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIP = '*';
    private static final char DIVIDE = '/';
    private static final char UNARY_MINUS = '-';
    private static final char EXPONENT = '^';
    private static final char L_PARENTHESIS = '(';
    private static final char R_PARENTHESIS = ')';
    private static final char SPACE = ' ';

    private InputCharBuffer inputBuffer;

    public RecursuveDescentExprBuilder() {
        this.inputBuffer = new InputCharBuffer();
    }

    private class InputCharBuffer {
        private Deque<Character> restOfInput;

        InputCharBuffer() {
            this.restOfInput = new ArrayDeque<Character>();
        }

        void load(@Nullable String expression) throws IllegalArgumentException {
            if (expression == null) {
                throw new IllegalArgumentException("Input string cannot be null");
            }
            if (!restOfInput.isEmpty()) {
                restOfInput.clear();
            }
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) != SPACE) {
                    restOfInput.add(expression.charAt(i));
                }
            }
        }

        char peek() {
            return restOfInput.getFirst();
        }

        void consume() {
            restOfInput.removeFirst();
        }

        char poll() {
            return restOfInput.pollFirst();
        }

        boolean isEmpty() {
            return restOfInput.isEmpty();
        }
    }

    @Override
    public Expr build(@Nullable String input) throws IllegalArgumentException {
        loadToBuffer(input);

        Expr result = valueOfExpr();

        if (!inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Something went wrong for " + input);
        }
        return result;
    }

    void loadToBuffer(@Nullable String input) {
        inputBuffer.load(input);
    }

    Expr valueOfExpr() throws IllegalArgumentException {
        Expr result = valueOfTerm();

        while (!inputBuffer.isEmpty()
                && (inputBuffer.peek() == PLUS || inputBuffer.peek() == MINUS)) {
            char operator = inputBuffer.poll();

            Expr right = valueOfTerm();

            if (operator == PLUS) {
                result = new Add(result, right);
            } else {
                result = new Subtract(result, right);
            }
        }
        return result;
    }

    Expr valueOfTerm() throws IllegalArgumentException {
        Expr result = valueOfFactor();

        while (!inputBuffer.isEmpty()
                && (inputBuffer.peek() == MULTIP || inputBuffer.peek() == DIVIDE)) {
            char operator = inputBuffer.poll();

            Expr right = valueOfFactor();

            if (operator == MULTIP) {
                result = new Multiply(result, right);
            } else {
                result = new Divide(result, right);
            }
        }
        return result;
    }

    Expr valueOfFactor() throws IllegalArgumentException {
        Expr result = valueOfPrimary();

        if (!inputBuffer.isEmpty() && inputBuffer.peek() == EXPONENT) {
            inputBuffer.consume();

            Expr right = valueOfFactor();

            return new Power(result, right);
        } else {
            return result;
        }
    }

    Expr valueOfPrimary() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        if (Character.isDigit(inputBuffer.peek())) {
            StringBuilder digits = new StringBuilder();

            do {
                digits.append(inputBuffer.poll());
            } while (!inputBuffer.isEmpty() && Character.isDigit(inputBuffer.peek()));

            int number = Integer.parseInt(digits.toString());

            return new Const(number);
        } else if (inputBuffer.peek() == L_PARENTHESIS) {
            inputBuffer.consume();

            Expr insideBrackets = valueOfExpr();

            if (inputBuffer.isEmpty() || inputBuffer.poll() != R_PARENTHESIS) {
                throw new IllegalArgumentException("Brackets are not closed");
            }
            return insideBrackets;
        } else if (inputBuffer.peek() == UNARY_MINUS) {
            inputBuffer.consume();

            Expr insideUnaryMinus = valueOfPrimary();

            return new UnaryMinus(insideUnaryMinus);
        } else {
            throw new IllegalArgumentException("Can't parse character " + inputBuffer.peek());
        }
    }
}
