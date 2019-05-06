package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ExprBuilderImpl implements ExprBuilder {

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static final char DIVISION = '/';
    private static final char POW = '^';
    private static final char UNAR_MINUS = 'M';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static Deque<Expr> expr = new ArrayDeque<>();

    @Override
    public Expr build(String input) {

        expr.clear();
        String readyString = expressionToPostFix(input);
        StringTokenizer tokenizer = new StringTokenizer(readyString, " ");

        while (tokenizer.hasMoreTokens()) {

            String token = tokenizer.nextToken();

            if (getPriority(token.charAt(0)) == 0) {
                int operand = Integer.parseInt(token);
                expr.push(new Const(operand));
            } else {

                if (expr.isEmpty()) {
                    throw new IllegalArgumentException("Syntax error!");
                }
                Expr right = expr.pop();

                if (token.charAt(0) == UNAR_MINUS) {
                    expr.push(new UnarMinus(right));
                } else {

                    if (expr.isEmpty()) {
                        throw new IllegalArgumentException("Syntax error!");
                    }
                    Expr left = expr.pop();

                    switch (token.charAt(0)) {
                        case PLUS:
                            expr.addFirst(new Add(left, right));
                            break;
                        case MINUS:
                            expr.addFirst(new Subtract(left, right));
                            break;
                        case MULTI:
                            expr.addFirst(new Multiply(left, right));
                            break;
                        case DIVISION:
                            expr.addFirst(new Division(left, right));
                            break;
                        case POW:
                            expr.addFirst(new Power(left, right));
                            break;
                        default:
                            throw new IllegalArgumentException("Error in expression!");
                    }
                }
            }
        }

        if (expr.size() != 1) {
            throw new IllegalArgumentException("Syntax error!");
        }

        return expr.peekFirst();
    }


    public static String expressionToPostFix(String input) {

        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null!");
        }

        String currentExpr = "";
        Deque<Character> exprPriority = new ArrayDeque<>();
        int priority;

        String resultString = input.replaceAll(" ", "");

        if (input == "") {
            throw new IllegalArgumentException("Input nothing ");
        }

        for (int i = 0; i < resultString.length(); i++) {
            priority = getPriority(resultString.charAt(i));

            if (priority == 0) {
                currentExpr += resultString.charAt(i);
            } else if (priority == 1) {
                exprPriority.addFirst(resultString.charAt(i));
            } else if (priority > 1) {
                currentExpr += ' ';
                while (!exprPriority.isEmpty()) {
                    if (getPriority(exprPriority.peekFirst()) >= priority) {
                        currentExpr += exprPriority.pollFirst();
                    } else {
                        break;
                    }
                    currentExpr += ' ';
                }
                exprPriority.addFirst(resultString.charAt(i));
            } else if (priority == -1) {

                while (!exprPriority.isEmpty() && getPriority(exprPriority.peekFirst()) != 1) {
                    currentExpr += ' ';
                    currentExpr += exprPriority.pollFirst();
                }
                if (exprPriority.isEmpty()) {
                    throw new IllegalArgumentException(" not left bracket");
                } else {
                    exprPriority.pollFirst();
                }
            }
        }

        while (!exprPriority.isEmpty()) {
            if (getPriority(exprPriority.peekFirst()) == 1) {
                throw new IllegalArgumentException(" Error");
            }
            currentExpr += ' ';
            currentExpr += exprPriority.pollFirst();
        }

        return currentExpr;
    }

    public static int getPriority(char token) {
        if (token == '1'
                || token == '2'
                || token == '3'
                || token == '4'
                || token == '5'
                || token == '6'
                || token == '7'
                || token == '8'
                || token == '9'
                || token == '0') {
            return 0;
        }
        if (token == UNAR_MINUS) {
            return 5;
        }
        if (token == POW) {
            return 4;
        }
        if (token == MULTI || token == DIVISION) {
            return 3;
        }
        if (token == PLUS || token == MINUS) {
            return 2;
        }
        if (token == OPEN_BRACKET) {
            return 1;
        }
        if (token == CLOSE_BRACKET) {
            return -1;
        } else {
            throw new IllegalArgumentException("No correct char in your expression!");
        }
    }
}