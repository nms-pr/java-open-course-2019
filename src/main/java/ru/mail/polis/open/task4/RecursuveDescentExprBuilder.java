package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.Nullable;

public class RecursuveDescentExprBuilder implements ExprBuilder {
    private InputBuffer inputBuffer;

    public RecursuveDescentExprBuilder() {
        this.inputBuffer = new InputBuffer();
    }

    private class InputBuffer {
        private Deque<Character> restOfInput;

        public InputBuffer() {
            this.restOfInput = new ArrayDeque<Character>();
        }

        public void load(String expression) {
            if (!restOfInput.isEmpty()) {
                restOfInput.clear();
            }
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) != ' ') {
                    restOfInput.add(expression.charAt(i));
                }
            }
        }

        public char getFirst() {
            return restOfInput.getFirst();
        }

        public void removeFirst() {
            restOfInput.removeFirst();
        }

        public char pollFirst() {
            return restOfInput.pollFirst();
        }

        public boolean isEmpty() {
            return restOfInput.isEmpty();
        }
    }

    @Override
    public Expr build(@Nullable String input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        inputBuffer.load(input);

        Expr result = tryToFormAddSubtract();

        if (!inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Something went wrong for " + input);
        }
        return result;
    }

    private Expr tryToFormAddSubtract() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        Expr result = tryToFormMultiplyDivide();

        while (true) {
            if (inputBuffer.isEmpty()) {
                return result;
            }

            char sign = inputBuffer.getFirst();

            if (sign != '+' && sign != '-') {
                return result;
            }

            inputBuffer.removeFirst();

            Expr right = tryToFormMultiplyDivide();

            if (sign == '+') {
                result = new Add(result, right);
            } else {
                result = new Subtract(result, right);
            }
        }
    }

    private Expr tryToFormMultiplyDivide() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        Expr result = tryToFormPower();

        while (true) {
            if (inputBuffer.isEmpty()) {
                return result;
            }

            char sign = inputBuffer.getFirst();

            if (sign != '*' && sign != '/') {
                return result;
            }

            inputBuffer.removeFirst();

            Expr right = tryToFormPower();

            if (sign == '*') {
                result = new Multiply(result, right);
            } else {
                result = new Divide(result, right);
            }
        }
    }

    private Expr tryToFormPower() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        Expr result = tryToFormBrackets();

        while (true) {
            if (inputBuffer.isEmpty()) {
                return result;
            }

            char sign = inputBuffer.getFirst();

            if (sign != '^') {
                return result;
            }

            inputBuffer.removeFirst();

            Expr right = tryToFormBrackets();

            result = new Power(result, right);
        }
    }

    private Expr tryToFormBrackets() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        if (inputBuffer.getFirst() == '(') {
            inputBuffer.removeFirst();

            Expr insideBrackets = tryToFormAddSubtract();

            if (inputBuffer.isEmpty() || inputBuffer.pollFirst() != ')') {
                throw new IllegalArgumentException("Brackets are not closed");
            }
            return insideBrackets;
        } else {
            return tryToFormConst();
        }
    }

    private Expr tryToFormConst() throws IllegalArgumentException {
        if (inputBuffer.isEmpty()) {
            throw new IllegalArgumentException("Buffer is empty");
        }

        boolean isNegative = false;

        // обработаем унарный минус
        if (inputBuffer.getFirst() == '-') {
            isNegative = true;
            inputBuffer.removeFirst();
        }

        StringBuilder digits = new StringBuilder();

        while (!inputBuffer.isEmpty() && Character.isDigit(inputBuffer.getFirst())) {
            digits.append(inputBuffer.pollFirst());
        }

        if (digits.length() == 0) {
            throw new IllegalArgumentException("Can't get valid number");
        }

        int number = Integer.parseInt(digits.toString());

        if (isNegative) {
            number = -number;
        }

        return new Const(number);
    }
}
