package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExprBuilderImpl implements ExprBuilder {

    private final char PLUS_SIGN = '+';
    private final char MINUS_SIGN = '-';
    private final char MULTIPLY_SIGN = '*';
    private final char DIVIDE_SIGN = '/';
    private final char POWER_SIGN = '^';
    private final char UNARY_MINUS_SIGN = 'm';
    private final char OPEN_BRACE = '(';
    private final char CLOSE_BRACE = ')';

    private final int ADDITIVE_OPERATION_PRIORITY = 1;
    private final int MULTIPLICATIVE_OPERATION_PRIORITY = 2;
    private final int POWER_OPERATION_PRIORITY = 3;
    private final int UNARY_OPERATION_PRIORITY = 4;

    private Deque<Expr> operands;
    private Deque<Character> operations;

    public ExprBuilderImpl() {

        operands = new ArrayDeque<>();
        operations = new ArrayDeque<>();

    }

    @Override
    public Expr build(String input) {

        String postfixForm = generatePostfixForm(input);

        operands.clear();

        for (int index = 0; index < postfixForm.length(); index++) {
            char currentChar = postfixForm.charAt(index);

            if (isOperand(currentChar)) {
                onOperandFound(currentChar);
            } else if (isOperation(currentChar)) {
                onOperationFound(currentChar);
            }
        }

        return operands.pop();
    }

    private void onOperandFound(char currentChar) {
        operands.push(
            new Const(
                Character.getNumericValue(currentChar)
            )
        );
    }

    /**
     * Creates new operand, corresponding to operation, and pushes it to stack
     *
     * @param currentChar - symbolic representation of operation to perform
     */
    private void onOperationFound(char currentChar) {
        switch (currentChar) {

            case PLUS_SIGN : {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Add(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case MINUS_SIGN : {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Subtract(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case MULTIPLY_SIGN : {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Multiply(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case DIVIDE_SIGN : {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Divide(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case POWER_SIGN : {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Power(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case UNARY_MINUS_SIGN : {
                operands.push(
                    new UnaryMinus(
                        operands.pop()
                    )
                );
                break;
            }
        }
    }

    /**
     * Checks whether the string is valid
     *
     * @param infixForm - string to validate
     */
    private void checkValidity(String infixForm) {

        if (infixForm.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        int openBraceCount = 0;
        int closedBraceCount = 0;
        int binaryOperationCount = 0;
        int operandsCount = 0;
        boolean wasBinaryOperationPresent = false;

        for (int index = 0; index < infixForm.length(); index++) {

            char currentChar = infixForm.charAt(index);

            if (currentChar == OPEN_BRACE) {
                openBraceCount++;
                continue;
            }

            if (currentChar == CLOSE_BRACE) {
                closedBraceCount++;
                continue;
            }

            if (isUnaryOperation(currentChar)) {
                continue;
            }

            if (isBinaryOperation(currentChar)) {
                binaryOperationCount++;

                if (!wasBinaryOperationPresent) {
                    wasBinaryOperationPresent = true;
                }
                continue;
            }

            if (isOperand(currentChar)) {
                operandsCount++;
                continue;
            }

            throw new IllegalArgumentException("Cannot resolve symbol: " + currentChar);
        }

        if (openBraceCount != closedBraceCount) {
            throw new IllegalArgumentException("Number of opened braces does not match number of closed ones");
        }

        if (operandsCount == 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (wasBinaryOperationPresent && (operandsCount - binaryOperationCount != 1)) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    /**
     * Creates postfix form from infix
     *
     * @param infixForm - valid source string
     * @return string in postfix form
     */
    private String generatePostfixForm(String infixForm) {

        String intermediateForm = generateSpacelessUnaryOperationsReplacedForm(infixForm);

        checkValidity(intermediateForm);

        operations.clear();
        StringBuilder postfix = new StringBuilder();

        for (int index = 0; index < intermediateForm.length(); index++) {

            char currentChar = intermediateForm.charAt(index);

            if (isOperand(currentChar)) {
                postfix.append(currentChar);
                continue;
            }

            if (currentChar == OPEN_BRACE) {
                operations.push(currentChar);
                continue;
            }

            if (currentChar == CLOSE_BRACE) {

                while (operations.peek() != OPEN_BRACE) {
                    postfix.append(operations.pop());
                }

                operations.pop();
                continue;

            }

            if (isOperation(currentChar)) {

                if (operations.isEmpty()) {
                    operations.push(currentChar);
                    continue;
                }

                if(
                    hasBiggerPriority(currentChar, operations.peek())
                    || isUnaryOperation(currentChar)
                ) {
                    operations.push(currentChar);
                } else {

                    postfix.append(operations.pop());
                    operations.push(currentChar);
                }
            }
        }

        while (!operations.isEmpty()) {
            postfix.append(operations.pop());
        }

        return postfix.toString();
    }

    /**
     *
     * Deletes all whitespaces and replaces unary minus operation '-' with 'm' (e.g. -5 -> m5)
     *
     * @param input - string to modify
     * @return modified string
     */
    private String generateSpacelessUnaryOperationsReplacedForm(String input) {

        operations.clear();
        StringBuilder spacelessForm = new StringBuilder();

        for (int index = 0; index < input.length(); index++) {

            char currentChar = input.charAt(index);

            if (currentChar == Character.SPACE_SEPARATOR) {
                continue;
            }

            if (isOperand(currentChar)) {
                spacelessForm.append(currentChar);
            }

            if (currentChar == MINUS_SIGN
                && (spacelessForm.length() == 0
                    || spacelessForm.charAt(spacelessForm.length() - 1) == OPEN_BRACE
                    || isOperation(spacelessForm.charAt(spacelessForm.length() - 1))
                )
            ) {
                spacelessForm.append(UNARY_MINUS_SIGN);
                continue;
            }

            if (isOperation(currentChar) || currentChar == OPEN_BRACE || currentChar == CLOSE_BRACE) {
                spacelessForm.append(currentChar);
                continue;
            }
        }

        return spacelessForm.toString();
    }

    private boolean hasBiggerPriority(char newOperation, Character storedOperation) {
        return getPriority(newOperation) > getPriority(storedOperation);
    }

    private int getPriority(Character operation) {

        switch (operation) {
            case OPEN_BRACE :
                return 0;

            case PLUS_SIGN :
            case MINUS_SIGN : {
                return ADDITIVE_OPERATION_PRIORITY;
            }

            case MULTIPLY_SIGN :
            case DIVIDE_SIGN : {
                return MULTIPLICATIVE_OPERATION_PRIORITY;
            }

            case POWER_SIGN : {
                return POWER_OPERATION_PRIORITY;
            }

            case UNARY_MINUS_SIGN : {
                return UNARY_OPERATION_PRIORITY;
            }

            default: {
                throw new IllegalArgumentException("Not an operation:" + operation);
            }
        }
    }

    private boolean isOperand(char operand) {
        return Character.isDigit(operand);
    }

    private boolean isOperation(char operation) {
        return isUnaryOperation(operation) || isBinaryOperation(operation);
    }

    private boolean isUnaryOperation(char operation) {
        return operation == UNARY_MINUS_SIGN;
    }

    private boolean isBinaryOperation(char operation) {
        return (operation == PLUS_SIGN) || (operation == MINUS_SIGN) ||
            (operation == MULTIPLY_SIGN) || (operation == DIVIDE_SIGN) ||
            (operation == POWER_SIGN);
    }
}
