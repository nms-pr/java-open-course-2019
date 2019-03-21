package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class ExprBuilderImplements implements ExprBuilder {

    public ExprBuilderImplements() {
        operationsPriority = new HashMap<>();
        operationsPriority.put("-", 1);
        operationsPriority.put("^", 2);
        operationsPriority.put("*", 3);
        operationsPriority.put("/", 3);
        operationsPriority.put("+", 4);
        operationsPriority.put("—", 4);
    }

    private Map<String, Integer> operationsPriority;

    public String transformationExpression (String expression,
                                            Map<String, Integer> operations,
                                            String leftBracket,
                                            String rightBracket) {
        if (expression == null
            || expression.length() == 0
            || operations == null
            || operations.isEmpty()) {
            throw new IllegalArgumentException("Incorrect input expression");
        }

        List<String> symbolsOfExpression = new ArrayList<>();
        Deque<String> operationOfExpression = new ArrayDeque<>();

        expression = expression.replace(" ", "");

        Set<String> symbolsNotOperand = new HashSet<>(operations.keySet());
        symbolsNotOperand.add(leftBracket);
        symbolsNotOperand.add(rightBracket);

        int indexOfEndsParsingStringLastIteration = 0;
        boolean findNext = true;

        while(findNext) {
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
                    symbolsOfExpression.add(
                        expression.substring(
                            indexOfEndsParsingStringLastIteration,
                            nextOperationIndex
                        )
                    );
                }

                if (nextOperation.equals(leftBracket)) {
                    operationOfExpression.push(nextOperation);
                } else if (nextOperation.equals(rightBracket)) {
                    while (!operationOfExpression.peek().equals(leftBracket)) {
                        symbolsOfExpression.add(operationOfExpression.pop());
                        if (operationOfExpression.isEmpty()) {
                            throw new IllegalArgumentException("Unmatched brackets");
                        }
                    }
                    operationOfExpression.pop();
                } else {
                    while (!operationOfExpression.isEmpty()
                        && !operationOfExpression.peek().equals(leftBracket)
                        && operations.get(nextOperation)
                        >= operations.get(operationOfExpression.peek())) {

                        symbolsOfExpression.add(operationOfExpression.pop());
                    }
                    operationOfExpression.push(nextOperation);
                }
                indexOfEndsParsingStringLastIteration
                    = nextOperationIndex
                    + nextOperation.length();
            }
        }
        if (indexOfEndsParsingStringLastIteration != expression.length()) {
            symbolsOfExpression.add(
                expression.substring(
                    indexOfEndsParsingStringLastIteration
                )
            );
        }

        while (!operationOfExpression.isEmpty()) {
            symbolsOfExpression.add(operationOfExpression.pop());
        }

        StringBuffer result = new StringBuffer();
        if (!symbolsOfExpression.isEmpty()) {
            result.append(symbolsOfExpression.remove(0));
        }

        while (!symbolsOfExpression.isEmpty()) {
            result.append(" ").append(symbolsOfExpression.remove(0));
        }

        return result.toString();
    }

    public String transformationExpression(String expression, Map<String, Integer> operations) {
        return transformationExpression(expression, operations, "(", ")");
    }

    @Override
    public Expr build(String input) {
        String expressionAfterTransformation = transformationExpression(input, operationsPriority);
        StringTokenizer tokenizer = new StringTokenizer(expressionAfterTransformation, " ");
        Deque<Expr> deque = new ArrayDeque<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (!operationsPriority.keySet().contains(token)) {
                int operand = Integer.parseInt(token.trim());
                deque.push(new Const(operand));
            } else {
                //may be something wrong
                Expr operand2 = deque.pop();
                if (token.equals("-")){
                    deque.push(new UnMin(operand2));
                } else {
                    if (!deque.isEmpty()) {
                        Expr operand1 = deque.pop();

                        if (token.equals("^")) {
                            deque.push(
                                new Pow(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals("*")) {
                            deque.push(
                                new Mult(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals("/")) {
                            deque.push(
                                new Div(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals("+")) {
                            deque.push(
                                new Add(
                                    operand1,
                                    operand2
                                )
                            );
                        } else if (token.equals("—")) {
                            deque.push(
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
        if (deque.size() != 1) {
            throw new IllegalArgumentException("Expression syntax error.");
        }
        return deque.pop();
    }

    public static void main(String[] args) {
        ExprBuilderImplements test = new ExprBuilderImplements();
        Expr result = test.build("- ((- 5 + 1) ^ 2 * 10 —  - 15) / (3 + 10) * 14");
        System.out.println(result.evaluate());
    }
}
