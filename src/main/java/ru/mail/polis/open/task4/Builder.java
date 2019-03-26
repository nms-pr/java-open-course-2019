package ru.mail.polis.open.task4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;

public final class Builder implements ExprBuilder, Expr {

    private final @NotNull Expr value;

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static final char DIVISION = '/';
    private static final char POWER = '^';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';
    private final Operation delimiter;
    private final ArrayList<Operation> operations;
    private int lastPosition;

    Builder(@Nullable String input) {
        delimiter = new Operation(-1, '#');
        lastPosition = -1;
        operations = getOperationDeque(input);
        value = build(input);
    }

    @SuppressWarnings("DuplicateExpressions")
    @Override
    public Expr build(@Nullable String input) {

        if (input.equals("")) {
            return new Const(0);
        }
        Operation operation;
        int lastPos = lastPosition;
        boolean isBracket = false;
        int index = -1;
        do {
            index++;
            isBracket = operations.get(index).position == -1 || isBracket;
            operation = operations.get(index);
        } while ((operation.position > lastPos + input.length()
                || operation.position <= lastPos) && index + 1 < operations.size());

        if (input.indexOf(operation.symbol) != -1) {
            int brack = input.indexOf(LEFT_BRACKET);
            String trimmed = input.trim();
            if (isBracket && ((trimmed.charAt(0) == LEFT_BRACKET
                    && trimmed.charAt(trimmed.length() - 1) == RIGHT_BRACKET))) {
                operations.remove(operations.indexOf(delimiter));
                return build(input.substring(brack + 1, input.lastIndexOf(RIGHT_BRACKET)), lastPos + brack + 1);
            }
            operations.remove(index);
            switch (operation.symbol) {
                case PLUS:
                    return new Add(build(input.substring(0, operation.position - lastPos - 1), lastPos),
                            build(input.substring(operation.position - lastPos), operation.position));
                case MINUS:
                    return new Subtraction(build(input.substring(0, operation.position - lastPos - 1), lastPos),
                            build(input.substring(operation.position - lastPos), operation.position));
                case MULTI:
                    return new Multiplication(build(input.substring(0, operation.position - lastPos - 1), lastPos),
                            build(input.substring(operation.position - lastPos), operation.position));
                case DIVISION:
                    return new Division(build(input.substring(0, operation.position - lastPos - 1), lastPos),
                            build(input.substring(operation.position - lastPos), operation.position));
                case POWER:
                    return new Power(build(input.substring(0, operation.position - lastPos - 1), lastPos),
                            build(input.substring(operation.position - lastPos), operation.position));
                default:
                    break;
            }
        }
        return new Const(Integer.parseInt(input.trim()));
    }

    private Expr build(String input, int operPos) {
        lastPosition = operPos;
        return build(input);
    }

    private ArrayList<Operation> getOperationDeque(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        } else if (input.equals("")) {
            throw new IllegalArgumentException("Input must not be empty");
        }
        char[] inputArray = input.toCharArray();
        int maxLevel = 0;
        int countBracket = 0;
        int numRight;
        int numLeft;
        ArrayList<Operation> operations = new ArrayList<>(inputArray.length / 2);
        ArrayList<ArrayDeque<Operation>[]> arrayDeques = new ArrayList<>(1);
        arrayDeques.add(init(inputArray.length / 2, 3));
        for (int index = 0; index < inputArray.length; index++) {
            numRight = inputArray.length;
            numLeft = input.indexOf(LEFT_BRACKET, index);
            if (numLeft == -1) {
                numLeft = numRight;
            }

            for (; index < numLeft; index++) {
                switch (inputArray[index]) {
                    case MINUS:
                        arrayDeques.get(0)[0].addFirst(new Operation(index, MINUS));
                        break;
                    case PLUS:
                        arrayDeques.get(0)[0].addFirst(new Operation(index, PLUS));
                        break;
                    case MULTI:
                        arrayDeques.get(0)[1].addFirst(new Operation(index, MULTI));
                        break;
                    case DIVISION:
                        arrayDeques.get(0)[1].addFirst(new Operation(index, DIVISION));
                        break;
                    case POWER:
                        arrayDeques.get(0)[2].addFirst(new Operation(index, POWER));
                        break;
                    default:
                        break;
                }
            }

            for (; index < numRight; index++) {
                if (inputArray[index] == LEFT_BRACKET) {
                    countBracket++;
                    if (countBracket > maxLevel) {
                        maxLevel++;
                        arrayDeques.add(init(input.length() / 2, 4));
                    }
                    arrayDeques.get(countBracket)[0].addFirst(delimiter);
                } else if (inputArray[index] == RIGHT_BRACKET) {
                    countBracket--;
                } else {
                    switch (inputArray[index]) {
                        case MINUS:
                            arrayDeques.get(countBracket)[1].addFirst(new Operation(index, MINUS));
                            break;
                        case PLUS:
                            arrayDeques.get(countBracket)[1].addFirst(new Operation(index, PLUS));
                            break;
                        case MULTI:
                            arrayDeques.get(countBracket)[2].addFirst(new Operation(index, MULTI));
                            break;
                        case DIVISION:
                            arrayDeques.get(countBracket)[2].addFirst(new Operation(index, DIVISION));
                            break;
                        case POWER:
                            arrayDeques.get(countBracket)[3].addFirst(new Operation(index, POWER));
                            break;
                        default:
                            break;
                    }
                }
                if (countBracket == 0) {
                    numRight = index;
                    index--;
                } else if (countBracket < 0) {
                    throw new IllegalArgumentException("Incorrect bracket sequence");
                }
            }
            if (countBracket != 0) {
                throw new IllegalArgumentException("Incorrect bracket sequence");
            }
        }
        for (int i = 0; i < 3; i++) {
            operations.addAll(arrayDeques.get(0)[i]);
        }
        for (int i = 1; i <= maxLevel; i++) {
            for (int j = 0; j < 4; j++) {
                operations.addAll(arrayDeques.get(i)[j]);
            }
        }
        operations.add(new Operation(-2, '$'));
        return operations;
    }

    private ArrayDeque<Operation>[] init(int length, int arrLength) {
        ArrayDeque<Operation>[] arrOperations = new ArrayDeque[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arrOperations[i] = new ArrayDeque<>(length);
        }
        return arrOperations;
    }

    @Override
    public int evaluate() {
        return value.evaluate();
    }

    private class Operation {
        final int position;
        final char symbol;

        Operation(int position, char symbol) {
            this.position = position;
            this.symbol = symbol;
        }
    }
}
