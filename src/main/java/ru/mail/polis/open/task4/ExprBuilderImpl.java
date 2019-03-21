package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExprBuilderImpl implements ExprBuilder {

    private Deque<Expr> operands;
    private Deque<Character> operations;

    public ExprBuilderImpl() {
        operands = new ArrayDeque<>();
        operations = new ArrayDeque<>();
    }

    @Override
    public Expr build(String input) {

        operands.clear();
        String postfixForm = generatePostfixForm(input);

        for (int index = 0; index < postfixForm.length(); index++) {

            if (isOperand(postfixForm.charAt(index))) {
                operands.push(
                    new Const(
                        Character.getNumericValue(postfixForm.charAt(index))
                    )
                );

            } else if (isOperation(postfixForm.charAt(index))) {
                switch (postfixForm.charAt(index)) {
                    case '+' : {
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
                    case '-' : {
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
                    case '*' : {
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
                    case '/' : {
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
                    case '^' : {
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
                    case 'm' : {
                        operands.push(
                            new UnaryMinus(
                                operands.pop()
                            )
                        );
                        break;
                    }
                }
            }

        }

        return operands.pop();
    }

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

            if (currentChar == '(') {
                openBraceCount++;
                continue;
            }
            if (currentChar == ')') {
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

    private String generatePostfixForm(String infixForm) {


        String intermediateForm = generateSpacelessUnaryReplacedForm(infixForm);
        checkValidity(intermediateForm);

        StringBuilder postfix = new StringBuilder();
        operations.clear();
        for (int index = 0; index < intermediateForm.length(); index++) {
            char currentChar = intermediateForm.charAt(index);

            if (isOperand(currentChar)) {
                postfix.append(currentChar);
                continue;
            }

            if ((currentChar == '(') || (currentChar == ')') || (isOperation(currentChar))) {
                if (currentChar == '(') {
                    operations.push(currentChar);
                    continue;
                }

                if (currentChar == ')') {
                    while (operations.peek() != '(') {
                        postfix.append(operations.pop());
                    }
                    operations.pop();
                    continue;
                }

                if (operations.isEmpty()) {
                    operations.push(currentChar);
                    continue;
                }

                if(hasMorePriority(currentChar, operations.peek()) || currentChar == 'm') {
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

    private String generateSpacelessUnaryReplacedForm(String infixForm) {
        operations.clear();
        StringBuilder spacelessForm = new StringBuilder();
        for (int index = 0; index < infixForm.length(); index++) {
            char currentChar = infixForm.charAt(index);

            if (currentChar == ' ') {
                continue;
            }

            if (isOperand(currentChar)) {
                spacelessForm.append(currentChar);
            }

            if (currentChar == '-' && (spacelessForm.length() == 0 || spacelessForm.charAt(spacelessForm.length() - 1) == '(' || isOperation(spacelessForm.charAt(spacelessForm.length() - 1)))) {
                spacelessForm.append('m');
                continue;
            }

            if (isOperation(currentChar) || currentChar == '(' || currentChar == ')') {
                spacelessForm.append(currentChar);
                continue;
            }
        }

        return spacelessForm.toString();
    }

    private boolean hasMorePriority(char newOperation, Character storedOperation) {
        return getPriority(newOperation) > getPriority(storedOperation);
    }

    private int getPriority(Character operation) {
        switch (operation) {
            case '(' :
                return 0;

            case '+' :
            case '-' : {
                return 1;
            }

            case '*' :
            case '/' : {
                return 2;
            }

            case '^' : {
                return 3;
            }

            case 'm' : {
                return 4;
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
        return operation == 'm';
    }

    private boolean isBinaryOperation(char operation) {
        return (operation == '+') || (operation == '-') ||
            (operation == '*') || (operation == '/') ||
            (operation == '^');
    }
}
