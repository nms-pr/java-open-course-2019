package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExprBuilderImpl implements ExprBuilder {

    private static final char PLUS_SIGN = '+';
    private static final char MINUS_SIGN = '-';
    private static final char MULTIPLY_SIGN = '*';
    private static final char DIVIDE_SIGN = '/';
    private static final char POWER_SIGN = '^';
    private static final char UNARY_MINUS_SIGN = 'm';
    private static final char OPEN_BRACE = '(';
    private static final char CLOSE_BRACE = ')';

    private static final char OPERAND_FRONT_SEPARATOR = '{';
    private static final char OPERAND_END_SEPARATOR = '}';

    private static final int ADDITIVE_OPERATION_PRIORITY = 1;
    private static final int MULTIPLICATIVE_OPERATION_PRIORITY = 2;
    private static final int POWER_OPERATION_PRIORITY = 3;
    private static final int UNARY_OPERATION_PRIORITY = 4;

    private Deque<Expr> operands;
    private Deque<Character> operations;

    public ExprBuilderImpl() {

        operands = new ArrayDeque<>();
        operations = new ArrayDeque<>();

    }

    @Override
    public Expr build(String input) {

        String postfixForm = generatePostfixForm(input);

        checkValidity(postfixForm);

        operands.clear();

        for (int index = 0; index < postfixForm.length(); index++) {
            char currentChar = postfixForm.charAt(index);

            if (isBeginningOfOperand(currentChar)) {
                onOperandFound(postfixForm, index);
            } else if (isOperation(currentChar)) {
                onOperationFound(currentChar);
            }
        }

        return operands.pop();
    }

    private void onOperandFound(String string, int startIndex) {
        startIndex++;
        int endIndex = startIndex;
        while (string.charAt(endIndex) != OPERAND_END_SEPARATOR) {
            endIndex++;
        }

        operands.push(
            new Const(
                Integer.parseInt(string.substring(startIndex, endIndex))
            )
        );
    }

    /**
     * Creates new operand, corresponding to operation, and pushes it to stack
     *
     * @param operation - symbolic representation of operation to perform
     */
    private void onOperationFound(char operation) {
        switch (operation) {

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

            default : {
                throw new IllegalArgumentException("No such operation found:" + operation);
            }
        }
    }


    /**
     * Checks whether the string is valid
     *
     * @param postfixForm - string to validate
     */
    private void checkValidity(String postfixForm) {

        if (postfixForm.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        int openBraceCount = 0;
        int closedBraceCount = 0;
        int binaryOperationCount = 0;
        int operandsCount = 0;
        boolean wasBinaryOperationPresent = false;

        for (int index = 0; index < postfixForm.length(); index++) {

            char currentChar = postfixForm.charAt(index);

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

            if (isBeginningOfOperand(currentChar)) {
                operandsCount++;
                continue;
            }

            if (Character.isDigit(currentChar) || currentChar == OPERAND_END_SEPARATOR) {
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
     * Creates postfix form from infix,
     * surrounds operands with | | (e.g. 5 -> |5|) to handle multi-character numbers
     *
     * @param infixForm - valid source string
     * @return string in postfix form
     */
    private String generatePostfixForm(String infixForm) {

        String intermediateForm = generateSpacelessUnaryOperationsReplacedForm(infixForm);

        operations.clear();
        StringBuilder postfix = new StringBuilder();

        for (int index = 0; index < intermediateForm.length(); index++) {

            char currentChar = intermediateForm.charAt(index);

            int endIndex = index;
            while (Character.isDigit(intermediateForm.charAt(endIndex))) {
                endIndex++;

                if (endIndex == intermediateForm.length()) {
                    break;
                }
            }

            if (endIndex != index) {
                postfix.append(OPERAND_FRONT_SEPARATOR)
                    .append(intermediateForm, index, endIndex)
                    .append(OPERAND_END_SEPARATOR);
                index = endIndex - 1;
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

                if (
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

            if (Character.isDigit(currentChar)) {
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

    private boolean isBeginningOfOperand(char operand) {
        return operand == OPERAND_FRONT_SEPARATOR;
    }

    private boolean isOperation(char operation) {
        return isUnaryOperation(operation) || isBinaryOperation(operation);
    }

    private boolean isUnaryOperation(char operation) {
        return operation == UNARY_MINUS_SIGN;
    }

    private boolean isBinaryOperation(char operation) {
        return (operation == PLUS_SIGN) || (operation == MINUS_SIGN)
            || (operation == MULTIPLY_SIGN) || (operation == DIVIDE_SIGN)
            || (operation == POWER_SIGN);
    }
}
