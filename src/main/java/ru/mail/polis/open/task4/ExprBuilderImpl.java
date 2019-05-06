package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class ExprBuilderImpl implements ExprBuilder {

    private static final char ADD = '+';
    private static final char MIN = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVIDE = '/';
    private static final char POWER = '^';
    private static final char UN_MIN = '_';
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    private static int countOfBrackets = 0;

    @Override
    public Expr build(@Nullable String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Wrong input.");
        }
        String replaceInput = input.replaceAll(" ", "");
        if (replaceInput.charAt(0) == LEFT_BRACKET
                && replaceInput.charAt(replaceInput.length() - 1) == RIGHT_BRACKET) {
            for (int i = 0; i < replaceInput.length(); i++) {
                bracketCounter(replaceInput, i);
                if (countOfBrackets == 0) {
                    if (i == replaceInput.length() - 1) {
                        replaceInput = replaceInput.substring(1, replaceInput.length() - 1);
                    } else {
                        break;
                    }
                }

            }
        }
        for (int i = 0; i < replaceInput.length(); i++) {
            bracketCounter(replaceInput, i);
            if (countOfBrackets == 0) {
                switch (replaceInput.charAt(i)) {
                    case ADD:
                        return new Add(build(replaceInput.substring(0, i)),
                                build(replaceInput.substring(i + 1)));
                    case MIN:
                        return new Subtraction(build(replaceInput.substring(0, i)),
                                build(replaceInput.substring(i + 1)));
                    default:
                        break;
                }
            }
        }
        if (countOfBrackets != 0) {
            countOfBrackets = 0;
            throw new IllegalArgumentException("Wrong number of brackets");
        }
        for (int i = 0; i < replaceInput.length(); i++) {
            bracketCounter(replaceInput, i);
            if (countOfBrackets == 0) {
                switch (replaceInput.charAt(i)) {
                    case MULTIPLICATION:
                        return new Multiplication(build(replaceInput.substring(0, i)),
                                build(replaceInput.substring(i + 1)));
                    case DIVIDE:
                        return new Divide(build(replaceInput.substring(0, i)),
                                build(replaceInput.substring(i + 1)));
                    default:
                        break;
                }
            }
        }
        for (int i = 0; i < replaceInput.length(); i++) {
            bracketCounter(replaceInput, i);
            if (countOfBrackets == 0) {
                if (replaceInput.charAt(i) == POWER) {
                    return new Power(build(replaceInput.substring(0, i)),
                            build(replaceInput.substring(i + 1)));
                }
            }
        }
        if (replaceInput.charAt(0) == UN_MIN) {
            return new UnMinus(Integer.parseInt(replaceInput.substring(1)));
        }
        for (int i = 0; i < replaceInput.length(); i++) {
            if (!Character.isDigit(replaceInput.charAt(i))) {
                throw new IllegalArgumentException("Wrong input");
            }
        }
        return new Const(Integer.parseInt(replaceInput));
    }

    private void bracketCounter(String replaceInput, int i) {
        if (replaceInput.charAt(i) == LEFT_BRACKET) {
            countOfBrackets++;
        } else if (replaceInput.charAt(i) == RIGHT_BRACKET) {
            countOfBrackets--;
        }
    }
}
