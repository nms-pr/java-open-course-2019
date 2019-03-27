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
    private static final Operation DELIMITER;
    private final ArrayList<Operation> operations;
    private int lastPosition;

    static {
        DELIMITER = new Operation(-1, '(');
    }

    Builder(@Nullable String input) {
        lastPosition = -1;
        operations = getOperationsQueue(input);
        value = build(input);
    }

    @Override
    public Expr build(@Nullable String input) {

        if (input.equals("")) {
            return new Const(0);
        }
        Operation operation;
        int lastPosition = this.lastPosition;
        boolean isBracket = false;
        int index = -1;
        do {
            index++;
            isBracket = operations.get(index).position == -1 || isBracket;
            operation = operations.get(index);
        } while ((operation.position > lastPosition + input.length()
                || operation.position <= lastPosition) && index + 1 < operations.size());

        if (input.indexOf(operation.symbol) != -1) {
            int bracketPosition = input.indexOf(LEFT_BRACKET);
            String trimmed = input.trim();
            if (isBracket && ((trimmed.charAt(0) == LEFT_BRACKET
                    && trimmed.charAt(trimmed.length() - 1) == RIGHT_BRACKET))) {
                operations.remove(DELIMITER);
                return build(input.substring(bracketPosition + 1, input.lastIndexOf(RIGHT_BRACKET)),
                        lastPosition + bracketPosition + 1);
            }
            operations.remove(index);
            String leftSubstring = input.substring(0, operation.position - lastPosition - 1);
            String rightSubstring = input.substring(operation.position - lastPosition);
            switch (operation.symbol) {
                case PLUS:
                    return new Add(build(leftSubstring, lastPosition),
                            build(rightSubstring, operation.position));
                case MINUS:
                    return new Subtraction(build(leftSubstring, lastPosition),
                            build(rightSubstring, operation.position));
                case MULTI:
                    return new Multiplication(build(leftSubstring, lastPosition),
                            build(rightSubstring, operation.position));
                case DIVISION:
                    return new Division(build(leftSubstring, lastPosition),
                            build(rightSubstring, operation.position));
                case POWER:
                    return new Power(build(leftSubstring, lastPosition),
                            build(rightSubstring, operation.position));
                default:
                    break;
            }
        }
        return new Const(Integer.parseInt(input.trim()));
    }

    private Expr build(String input, int currentPosition) {
        lastPosition = currentPosition;
        return build(input);
    }

    private ArrayList<Operation> getOperationsQueue(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        } else if (input.equals("")) {
            throw new IllegalArgumentException("Input must not be empty");
        }
        char[] inputArray = input.toCharArray();
        int maxBracketLevel = 0;
        int bracketCounter = 0;
        ArrayList<Operation> sortedOperationList = new ArrayList<>(inputArray.length / 2);
        ArrayList<ArrayDeque<Operation>[]> tempOperationList = new ArrayList<>(1);
        tempOperationList.add(initArray(inputArray.length / 2));
        for (int index = 0; index < inputArray.length; index++) {
            if (inputArray[index] == LEFT_BRACKET) {
                bracketCounter++;
                if (bracketCounter > maxBracketLevel) {
                    maxBracketLevel++;
                    tempOperationList.add(initArray(input.length() / 2));
                }
                tempOperationList.get(bracketCounter)[0].addFirst(DELIMITER);
            } else if (inputArray[index] == RIGHT_BRACKET) {
                bracketCounter--;
            } else {
                switch (inputArray[index]) {
                    case MINUS:
                        tempOperationList.get(bracketCounter)[1].addFirst(new Operation(index, MINUS));
                        break;
                    case PLUS:
                        tempOperationList.get(bracketCounter)[1].addFirst(new Operation(index, PLUS));
                        break;
                    case MULTI:
                        tempOperationList.get(bracketCounter)[2].addFirst(new Operation(index, MULTI));
                        break;
                    case DIVISION:
                        tempOperationList.get(bracketCounter)[2].addFirst(new Operation(index, DIVISION));
                        break;
                    case POWER:
                        tempOperationList.get(bracketCounter)[3].addFirst(new Operation(index, POWER));
                        break;
                    default:
                        break;
                }
            }
        }
        if (bracketCounter != 0) {
            throw new IllegalArgumentException("Incorrect bracket sequence");
        }
        for (int i = 0; i <= maxBracketLevel; i++) {
            for (int j = 0; j < 4; j++) {
                sortedOperationList.addAll(tempOperationList.get(i)[j]);
            }
        }
        sortedOperationList.add(new Operation(-2, '$'));
        return sortedOperationList;
    }

    private ArrayDeque<Operation>[] initArray(int dequeLength) {
        ArrayDeque<Operation>[] arrOperations = new ArrayDeque[4];
        for (int i = 0; i < 4; i++) {
            arrOperations[i] = new ArrayDeque<>(dequeLength);
        }
        return arrOperations;
    }

    @Override
    public int evaluate() {
        return value.evaluate();
    }
}
