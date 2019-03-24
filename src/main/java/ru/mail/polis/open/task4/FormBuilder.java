package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

class FormBuilder {

    static final char PLUS_SIGN = '+';
    static final char MINUS_SIGN = '-';
    static final char MULTIPLY_SIGN = '*';
    static final char DIVIDE_SIGN = '/';
    static final char POWER_SIGN = '^';
    static final char UNARY_MINUS_SIGN = 'm';
    private static final char OPEN_BRACE = '(';
    private static final char CLOSE_BRACE = ')';
    private static final int ADDITIVE_OPERATION_PRIORITY = 1;
    private static final int MULTIPLICATIVE_OPERATION_PRIORITY = 2;
    private static final int POWER_OPERATION_PRIORITY = 3;
    private static final int UNARY_OPERATION_PRIORITY = 4;

    private static Deque<String> operations;
    private static HashMap<Character, Integer> operationToPriority;

    static {
        operations = new ArrayDeque<>();

        operationToPriority = new HashMap<>();
        operationToPriority.put(PLUS_SIGN, ADDITIVE_OPERATION_PRIORITY);
        operationToPriority.put(MINUS_SIGN, ADDITIVE_OPERATION_PRIORITY);
        operationToPriority.put(MULTIPLY_SIGN, MULTIPLICATIVE_OPERATION_PRIORITY);
        operationToPriority.put(DIVIDE_SIGN, MULTIPLICATIVE_OPERATION_PRIORITY);
        operationToPriority.put(POWER_SIGN, POWER_OPERATION_PRIORITY);
        operationToPriority.put(UNARY_MINUS_SIGN, UNARY_OPERATION_PRIORITY);
        operationToPriority.put(OPEN_BRACE, 0);
    }

    private FormBuilder() {}

    static List<String> generatePostfixTokenForm(String input) {
        List<String> intermediateForm = generateIntermediateTokenForm(input);
        checkValidity(intermediateForm);

        return generatePostfixTokenForm(intermediateForm);
    }

    static List<String> generatePostfixTokenForm(List<String> intermediateForm) {

        operations.clear();
        List<String> postfix = new ArrayList<>();

        for (String token : intermediateForm) {
            if (Character.isDigit(token.charAt(0))) {
                postfix.add(token);
                continue;
            }

            if (token.charAt(0) == OPEN_BRACE) {
                operations.push(token);
                continue;
            }

            if (token.charAt(0) == CLOSE_BRACE) {
                while (operations.peek().charAt(0) != OPEN_BRACE) {
                    postfix.add(operations.pop());

                    if (operations.size() == 0) {
                        throw new IllegalArgumentException("Number of open braces is less than number of closed");
                    }
                }

                operations.pop();
                continue;
            }

            if (isOperation(token)) {
                if (operations.isEmpty()) {
                    operations.push(token);
                    continue;
                }

                if (
                    hasBiggerPriority(token, operations.peek())
                        || isUnaryOperation(token)
                ) {
                    operations.push(token);
                } else {
                    postfix.add(operations.pop());
                    operations.push(token);
                }
            }
        }

        while (!operations.isEmpty()) {
            postfix.add(operations.pop());
        }

        return postfix;
    }

    private static void onInvalidOperation(String type, String before, String operation, String after) {
        // e.g. "Invalid binary operation ( / 5"
        throw new IllegalArgumentException("Invalid " + type + " operation: " + before + operation + after);
    }

    static void checkValidity(List<String> intermediateForm) {

        if (intermediateForm.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        int openBraceCount = 0;
        int closedBraceCount = 0;
        boolean hadOperands = false;

        for (int indexOfToken = 0; indexOfToken < intermediateForm.size(); indexOfToken++) {
            String token = intermediateForm.get(indexOfToken);

            if (token.charAt(0) == OPEN_BRACE) {
                openBraceCount++;
                continue;
            }

            if (token.charAt(0) == CLOSE_BRACE) {
                closedBraceCount++;
                continue;
            }

            if (Character.isDigit(token.charAt(0))) {
                hadOperands = true;
                continue;
            }

            if (isUnaryOperation(token)) {
                if (indexOfToken == intermediateForm.size() - 1) {
                    onInvalidOperation("unary", "", token, "[endOfString]");
                }

                String nextToken = intermediateForm.get(indexOfToken + 1);

                if (!Character.isDigit(nextToken.charAt(0))
                    && !(nextToken.charAt(0) == OPEN_BRACE)
                    && !isUnaryOperation(nextToken)
                ) {
                    onInvalidOperation("unary", "", token, nextToken);
                }
                continue;
            }

            if (isBinaryOperation(token)) {
                if (indexOfToken == 0) {
                    onInvalidOperation("binary",
                        "[beginningOfString]",
                        token,
                        intermediateForm.get(indexOfToken + 1)
                    );
                }
                if (indexOfToken == intermediateForm.size() - 1) {
                    onInvalidOperation("binary",
                        intermediateForm.get(indexOfToken - 1),
                        token,
                        "[endString]"
                    );
                }

                String previousToken = intermediateForm.get(indexOfToken - 1);
                String nextToken = intermediateForm.get(indexOfToken + 1);

                if (isOperation(previousToken)                                   // + /
                    || isOperation(nextToken)                                    // / +
                    || previousToken.charAt(0) == OPEN_BRACE                     // ( /
                    || nextToken.charAt(0) == CLOSE_BRACE                        // / )
                ) {
                    onInvalidOperation("binary", previousToken, token, nextToken);
                }
                continue;
            }

            throw new IllegalArgumentException("Cannot resolve token: " + token);

        }

        if (openBraceCount != closedBraceCount) {
            throw new IllegalArgumentException("Number of opened braces does not match number of closed ones");
        }

        if (!hadOperands) {
            throw new IllegalArgumentException("No operands in expression");
        }
    }

    static List<String> generateIntermediateTokenForm(String input) {

        operations.clear();
        ArrayList<String> intermediateForm = new ArrayList<>();

        for (int index = 0; index < input.length(); index++) {

            char currentChar = input.charAt(index);

            if (currentChar == ' ') {
                continue;
            }

            int endIndex = index;
            while (Character.isDigit(input.charAt(endIndex))) {
                endIndex++;

                if (endIndex == input.length()) {
                    break;
                }
            }

            if (index != endIndex) {
                intermediateForm.add(input.substring(index, endIndex));
                index = endIndex - 1;
                continue;
            }

            if (currentChar == MINUS_SIGN
                && (intermediateForm.size() == 0
                    || intermediateForm.get(intermediateForm.size() - 1).charAt(0) == OPEN_BRACE
                    || isOperation(intermediateForm.get(intermediateForm.size() - 1))
                )
            ) {
                intermediateForm.add(Character.toString(UNARY_MINUS_SIGN));
                continue;
            }

            if (isBinaryOperation(currentChar)
                || currentChar == OPEN_BRACE
                || currentChar == CLOSE_BRACE
            ) {
                intermediateForm.add(Character.toString(currentChar));
                continue;
            }

            throw new IllegalArgumentException("Cannot resolve symbol '" + currentChar + "'");
        }

        return intermediateForm;
    }

    private static boolean hasBiggerPriority(String newOperation, String storedOperation) {
        if (newOperation.length() > 1) {
            throw new IllegalArgumentException("Invalid operation " + newOperation);
        }
        if (storedOperation.length() > 1) {
            throw new IllegalArgumentException("Invalid operation " + storedOperation);
        }
        return operationToPriority.get(newOperation.charAt(0)) > operationToPriority.get(storedOperation.charAt(0));
    }

    private static boolean isOperation(String operation) {
        return isUnaryOperation(operation) || isBinaryOperation(operation);
    }

    private static boolean isUnaryOperation(String operation) {
        if (operation.length() > 1) {
            return false;
        }
        return operation.charAt(0) == UNARY_MINUS_SIGN;
    }

    private static boolean isBinaryOperation(String operation) {
        if (operation.length() > 1) {
            return false;
        }
        return isBinaryOperation(operation.charAt(0));
    }

    private static boolean isBinaryOperation(char operation) {
        return (operation == PLUS_SIGN) || (operation == MINUS_SIGN)
            || (operation == MULTIPLY_SIGN) || (operation == DIVIDE_SIGN)
            || (operation == POWER_SIGN);
    }
}