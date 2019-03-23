package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class ExprBuilderImplements implements ExprBuilder {

    public  ExprBuilderImplements() {
        operationOfExpression = new ArrayDeque<>();
        operationsPriority = new HashMap<>();
        expressionContainer = new ArrayDeque<>();
        operationsPriority.put("-", 1);
        operationsPriority.put("^", 2);
        operationsPriority.put("*", 3);
        operationsPriority.put("/", 3);
        operationsPriority.put("+", 4);
        operationsPriority.put("—", 4);
        symbolsNotOperand = new HashSet<>(operationsPriority.keySet());
        symbolsNotOperand.add(LEFT_BRACKET);
        symbolsNotOperand.add(RIGHT_BRACKET);
    }

    private static Map<String, Integer> operationsPriority;
    private Deque<String> operationOfExpression;
    private Set<String> symbolsNotOperand;
    private Deque<Expr> expressionContainer;

    private static final String ADD = "+";
    private static final String UN_MIN = "-";
    private static final String POW = "^";
    private static final String DIV = "/";
    private static final String MULT = "*";
    private static final String SUB = "—";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";


    String transformationExpression(String expression) {

        if (expression == null) {
            throw new IllegalArgumentException("You wrote a NULL that wrong");
        }

        expression = expression.replace(" ", "");

        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Incorrect input expression");
        }

        StringBuilder resultString = new StringBuilder();
        int indexOfEndsParsingStringLastIteration = 0;
        boolean findNext = true;

        while (findNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            for (String operation : symbolsNotOperand) {
                int i = expression.indexOf(operation,
                    indexOfEndsParsingStringLastIteration);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }

            if (nextOperationIndex == expression.length()) {
                findNext = false;
            } else {
                if (indexOfEndsParsingStringLastIteration != nextOperationIndex) {
                    resultString.append(
                        expression.substring(
                            indexOfEndsParsingStringLastIteration,
                            nextOperationIndex
                        )
                    ).append(" ");
                }

                if (nextOperation.equals(LEFT_BRACKET)) {
                    operationOfExpression.push(nextOperation);
                } else if (nextOperation.equals(RIGHT_BRACKET)) {
                    while (!operationOfExpression.peek().equals(LEFT_BRACKET)) {
                        resultString.append(operationOfExpression.pop()).append(" ");
                        if (operationOfExpression.isEmpty()) {
                            throw new IllegalArgumentException("Unmatched brackets");
                        }
                    }
                    operationOfExpression.pop();
                } else {
                    while (!operationOfExpression.isEmpty()
                        && !operationOfExpression.peek().equals(LEFT_BRACKET)
                        && operationsPriority.get(nextOperation)
                        >= operationsPriority.get(operationOfExpression.peek())) {

                        resultString.append(operationOfExpression.pop()).append(" ");
                    }
                    operationOfExpression.push(nextOperation);
                }
                indexOfEndsParsingStringLastIteration
                    = nextOperationIndex
                    + nextOperation.length();
            }
        }
        if (indexOfEndsParsingStringLastIteration != expression.length()) {
            resultString.append(
                expression.substring(
                    indexOfEndsParsingStringLastIteration
                )
            ).append(" ");
        }

        while (!operationOfExpression.isEmpty()) {
            resultString.append(operationOfExpression.pop()).append(" ");
        }
        resultString.deleteCharAt(resultString.lastIndexOf("") - 1);
        return resultString.toString();
    }

    @Override
    public Expr build(String input) {
        String expressionAfterTransformation = transformationExpression(input);
        StringTokenizer tokenizer = new StringTokenizer(expressionAfterTransformation, " ");
        expressionContainer.clear();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (!operationsPriority.containsKey(token)) {
                int operand = Integer.parseInt(token.trim());
                expressionContainer.push(new Const(operand));
            } else {
                Expr operand2 = expressionContainer.pop();
                if (token.equals(UN_MIN)) {
                    expressionContainer.push(new UnMin(operand2));
                } else {
                    if (!expressionContainer.isEmpty()) {
                        Expr operand1 = expressionContainer.pop();

                        if (token.equals(POW)) {
                            expressionContainer.push(
                                new Pow(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals(MULT)) {
                            expressionContainer.push(
                                new Mult(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals(DIV)) {
                            expressionContainer.push(
                                new Div(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals(ADD)) {
                            expressionContainer.push(
                                new Add(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals(SUB)) {
                            expressionContainer.push(
                                new Sub(
                                    operand1,
                                    operand2
                                )
                            );
                        }
                    } else {
                        throw new IllegalArgumentException("Incorrect data");
                    }
                }
            }
        }
        if (expressionContainer.size() != 1) {
            throw new IllegalArgumentException("Expression syntax error.");
        }
        return expressionContainer.pop();
    }
}
