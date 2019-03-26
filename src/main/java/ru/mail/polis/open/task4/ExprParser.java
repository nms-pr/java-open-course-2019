package ru.mail.polis.open.task4;

import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Nullable;
import ru.mail.polis.open.task4.expressions.*;

import java.lang.reflect.Array;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ExprParser implements ExprBuilder {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static final char ADD = '+';
    private static final char SUB = '—';
    private static final char MULT = '*';
    private static final char DIV = '/';
    private static final char POW = '^';
    private static final char MINUS = '-';
    private int bracketsCounter = 0;
    private char[] tokensArray;

    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        } else {
            input = input.replace(" ", "");
            return getExpression(input);
        }
    }

    Expr getExpression(String tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (tokens.startsWith(String.valueOf(OPEN_BRACKET))
            && tokens.endsWith(String.valueOf(CLOSE_BRACKET))
            && tokens.substring(1, tokens.length() - 1).matches("[0-9+*^/\\-]*\\(?[0-9+*^/\\-]+\\)?[0-9+*^/\\-]*$")) {
            tokens = tokens.substring(1, tokens.length() - 1);
            bracketsCounter = 0;
        }
        return addSub(tokens);
    }

    Expr addSub(String tokens) {
        String[] twoTokens;
        tokensArray = tokens.toCharArray();
        for (char s : tokensArray) {
            switch (s) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case ADD:
                    if (bracketsCounter == 0) {
                        twoTokens = splitIntoTwoParts(tokens, ADD);
                        return new Add(getExpression(twoTokens[0]), getExpression(twoTokens[1]));
                    } else {
                        break;
                    }
                case SUB:
                    if (bracketsCounter == 0) {
                        twoTokens = splitIntoTwoParts(tokens, SUB);
                        return new Sub(getExpression(twoTokens[0]), getExpression(twoTokens[1]));
                    } else {
                        break;
                    }
            }

        }
        return divMult(tokens);
    }

    Expr divMult(String tokens) {
        String[] twoTokens;
        tokensArray = tokens.toCharArray();
        for (char s : tokensArray) {
            switch (s) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case DIV:
                    if (bracketsCounter == 0) {
                        twoTokens = splitIntoTwoParts(tokens, DIV);
                        return new Div(getExpression(twoTokens[0]), getExpression(twoTokens[1]));
                    } else {
                        break;
                    }
                case MULT:
                    if (bracketsCounter == 0) {
                        twoTokens = splitIntoTwoParts(tokens, MULT);
                        return new Mult(getExpression(twoTokens[0]), getExpression(twoTokens[1]));
                    } else {
                        break;
                    }
            }

        }
        return pow(tokens);
    }

    Expr pow(String tokens) {
        String[] twoTokens;
        tokensArray = tokens.toCharArray();
        for (char s : tokensArray) {
            switch (s) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case POW:
                    if (bracketsCounter == 0) {
                        twoTokens = splitIntoTwoParts(tokens, POW);
                        return new Pow(getExpression(twoTokens[0]), getExpression(twoTokens[1]));
                    } else {
                        break;
                    }
            }
        }
        return minus(tokens);
    }

    Expr minus(String tokens) {
        if (tokens.startsWith(String.valueOf(MINUS))) {
            return new Minus(Integer.valueOf(tokens.substring(1)));
        } else {
            return constant(tokens);
        }
    }

    Expr constant(String tokens) {
        int constant;
        try {
            constant = Integer.parseInt(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return new Const(constant);
    }

    String[] splitIntoTwoParts(String tokens, char delimiter) {
        return tokens.split(Pattern.quote(String.valueOf(delimiter)), 2);
    }
}
