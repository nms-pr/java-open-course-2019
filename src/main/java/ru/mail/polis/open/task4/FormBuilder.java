package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

class FormBuilder {

    static final String PLUS_SIGN = "+";
    static final String MINUS_SIGN = "-";
    static final String MULTIPLY_SIGN = "*";
    static final String DIVIDE_SIGN = "/";
    static final String POWER_SIGN = "^";
    static final String UNARY_MINUS_SIGN = "m";
    private static final String OPEN_BRACE = "(";
    private static final String CLOSE_BRACE = ")";
    private static final int ADDITIVE_OPERATION_PRIORITY = 1;
    private static final int MULTIPLICATIVE_OPERATION_PRIORITY = 2;
    private static final int POWER_OPERATION_PRIORITY = 3;
    private static final int UNARY_OPERATION_PRIORITY = 4;

    private static Deque<String> operations;
    private static HashMap<String, Integer> operationToPriority;

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

            if (token.equals(OPEN_BRACE)) {
                operations.push(token);
                continue;
            }

            if (token.equals(CLOSE_BRACE)) {
                while (!operations.peek().equals(OPEN_BRACE)) {
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

    static void checkValidity(List<String> intermediateForm) {

        if (intermediateForm.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }

        int openBraceCount = 0;
        int closedBraceCount = 0;
        int operandsCount = 0;

        for (int i = 0; i < intermediateForm.size(); i++) {
            String token = intermediateForm.get(i);

            if (token.equals(OPEN_BRACE)) {
                openBraceCount++;
                continue;
            }

            if (token.equals(CLOSE_BRACE)) {
                closedBraceCount++;
                continue;
            }

            if (Character.isDigit(token.charAt(0))) {
                operandsCount++;
                continue;
            }

            if (isUnaryOperation(token)) {
                if (i == intermediateForm.size() - 1) {
                    throw new IllegalArgumentException("Invalid unary operation " + token + "[endString]");
                }
                if (!Character.isDigit(intermediateForm.get(i + 1).charAt(0)) && !intermediateForm.get(i + 1).equals(OPEN_BRACE) && !isUnaryOperation(intermediateForm.get(i + 1))) {
                    throw new IllegalArgumentException("Invalid unary operation " + token + intermediateForm.get(i + 1));
                }
                continue;
            }

            if (isBinaryOperation(token)) {
                if (i == 0) {
                    throw new IllegalArgumentException("Invalid binary operation [beginString]" + token + intermediateForm.get(i + 1));
                }
                if (i == intermediateForm.size() - 1) {
                    throw new IllegalArgumentException("Invalid binary operation " + intermediateForm.get(i - 1) + token + "[endString]");
                }
                if (isOperation(intermediateForm.get(i - 1)) || isOperation(intermediateForm.get(i + 1)) || intermediateForm.get(i - 1).equals(OPEN_BRACE) || intermediateForm.get(i+1).equals(CLOSE_BRACE)) {
                    throw new IllegalArgumentException("Invalid binary operation " + intermediateForm.get(i - 1) + token + intermediateForm.get(i + 1));
                }
                continue;
            }
            throw new IllegalArgumentException("Cannot resolve token: " + token);

        }

        if (openBraceCount != closedBraceCount) {
            throw new IllegalArgumentException("Number of opened braces does not match number of closed ones");
        }

        if (operandsCount == 0) {
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

            if (currentChar == MINUS_SIGN.charAt(0)
                && (intermediateForm.size() == 0
                || intermediateForm.get(intermediateForm.size() - 1).charAt(0) == OPEN_BRACE.charAt(0)
                || isOperation(intermediateForm.get(intermediateForm.size() - 1))
            )
            ) {
                intermediateForm.add(UNARY_MINUS_SIGN);
                continue;
            }

            if (isOperation(Character.toString(currentChar)) || currentChar == OPEN_BRACE.charAt(0) || currentChar == CLOSE_BRACE.charAt(0)) {
                intermediateForm.add(Character.toString(currentChar));
                continue;
            }

            throw new IllegalArgumentException("Cannot resolve symbol '" + currentChar + "'");
        }

        return intermediateForm;
    }


    private static boolean hasBiggerPriority(String newOperation, String storedOperation) {
        return operationToPriority.get(newOperation) > operationToPriority.get(storedOperation);
    }

    private static boolean isOperation(String operation) {
        return isUnaryOperation(operation) || isBinaryOperation(operation);
    }

    private static boolean isUnaryOperation(String operation) {
        return operation.equals(UNARY_MINUS_SIGN);
    }

    private static boolean isBinaryOperation(String operation) {
        return (operation.equals(PLUS_SIGN)) || (operation.equals(MINUS_SIGN))
            || (operation.equals(MULTIPLY_SIGN)) || (operation.equals(DIVIDE_SIGN))
            || (operation.equals(POWER_SIGN));
    }
}