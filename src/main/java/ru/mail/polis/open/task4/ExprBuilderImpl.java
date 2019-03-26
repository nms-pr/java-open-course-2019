package ru.mail.polis.open.task4;

import jdk.swing.interop.SwingInterOpUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;


public class ExprBuilderImpl implements ExprBuilder {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVISION = "/";
    private static final String MULTIPLY = "*";
    private static final String POW = "^";
    private static final String UNAR_MINUS = "-";
    private static final String OPEN_ROUND_BRACKET = "(";
    private static final String CLOSE_ROUND_BRACKET = ")";
    private static final int PRIORITY_OF_OPERATION_1 = 1;
    private static final int PRIORITY_OF_OPERATION_2 = 2;
    private static final int PRIORITY_OF_OPERATION_3 = 3;
    private static final int PRIORITY_OF_OPERATION_4 = 4;
    private static HashMap<String, Integer> listOfPriority;
    private HashSet<String> otherSymbols;
    private ArrayDeque<String> operationsOfExpr;
    private ArrayDeque<Expr> constructorOfExpression;

    public ExprBuilderImpl() {
        listOfPriority = new HashMap<>();
        otherSymbols = new HashSet<>(listOfPriority.keySet());
        operationsOfExpr = new ArrayDeque<>();
        constructorOfExpression = new ArrayDeque<>();
        listOfPriority.put(PLUS, PRIORITY_OF_OPERATION_4);
        listOfPriority.put(MINUS, PRIORITY_OF_OPERATION_4);
        listOfPriority.put(DIVISION, PRIORITY_OF_OPERATION_3);
        listOfPriority.put(MULTIPLY, PRIORITY_OF_OPERATION_3);
        listOfPriority.put(POW, PRIORITY_OF_OPERATION_2);
        listOfPriority.put(UNAR_MINUS, PRIORITY_OF_OPERATION_1);
        otherSymbols.add(OPEN_ROUND_BRACKET);
        otherSymbols.add(CLOSE_ROUND_BRACKET);
    }


    @Override
    public Expr build(@Nullable String input) {
        String fixedExpr = readyString(input);
        StringTokenizer tokenizer = new StringTokenizer(fixedExpr, " ");
        constructorOfExpression.clear();
        while (tokenizer.hasMoreTokens()) {
            String oneToken = tokenizer.nextToken();
            if (!listOfPriority.containsKey(oneToken)) {
                int operanda = Integer.parseInt(oneToken);
                constructorOfExpression.push(new Const(operanda));
            } else {
                Expr operanda2 = constructorOfExpression.pop();
                if (oneToken.equals(UNAR_MINUS) && constructorOfExpression.isEmpty()) {
                    constructorOfExpression.push(new UnarMinus(operanda2));

                } else {
                    if (!constructorOfExpression.isEmpty()) {
                        Expr operanda1 = constructorOfExpression.pop();
                        if (oneToken.equals(POW)) {
                            constructorOfExpression.push(new Power(operanda1, operanda2));
                        } else if (oneToken.equals(MULTIPLY)) {
                            constructorOfExpression.push(new Multiply(operanda1, operanda2));
                        } else if (oneToken.equals(DIVISION)) {
                            constructorOfExpression.push(new Division(operanda1, operanda2));
                        } else if (oneToken.equals(PLUS)) {
                            constructorOfExpression.push(new Add(operanda1, operanda2));
                        } else if (oneToken.equals(MINUS)) {
                            constructorOfExpression.push(new Subtract(operanda1, operanda2));
                        } else if (checkDigits(oneToken) == false) {
                            throw new IllegalArgumentException("Wrong symbol in expression.");
                        }
                    } else {
                        throw new IllegalArgumentException("Wrong expression");
                    }
                }
            }
        }
        if (constructorOfExpression.size() != 1) {
            throw new IllegalArgumentException("Error in the expression");
        }
        return constructorOfExpression.pop();
    }

    private String readyString(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("Expression cannot be null");
        }
        expression = expression.replace(" ", "");
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Wrong expression");
        }
        StringBuilder readyString = new StringBuilder();
        int indexOfLastOperation = 0;
        boolean searchingTheNext = true;
        while (searchingTheNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            for (String operation : otherSymbols) {
                int i = expression.indexOf(operation, indexOfLastOperation);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }
            if (nextOperationIndex == expression.length()) {
                searchingTheNext = false;
            } else {
                if (indexOfLastOperation != nextOperationIndex) {
                    readyString.append(expression.substring(indexOfLastOperation, nextOperationIndex)).append(" ");
                }
                if (nextOperation.equals(OPEN_ROUND_BRACKET)) {
                    operationsOfExpr.push(nextOperation);
                } else if (nextOperation.equals(CLOSE_ROUND_BRACKET)) {
                    while (!operationsOfExpr.peek().equals(OPEN_ROUND_BRACKET)) {
                        readyString.append(operationsOfExpr.pop()).append(" ");
                        if (operationsOfExpr.isEmpty()) {
                            throw new IllegalArgumentException("You put expression in brackets that do not match.");
                        }
                    }
                    operationsOfExpr.pop();
                } else {
                    while (!operationsOfExpr.isEmpty()
                            && !operationsOfExpr.peek().equals(OPEN_ROUND_BRACKET)
                            && listOfPriority.get(nextOperation)
                            >= listOfPriority.get(operationsOfExpr.peek())) {
                        readyString.append(operationsOfExpr.pop()).append(" ");
                    }
                    operationsOfExpr.push(nextOperation);
                }
                indexOfLastOperation = nextOperationIndex + nextOperation.length();
            }
        }
        if (indexOfLastOperation != expression.length()) {
            readyString.append(expression.substring(indexOfLastOperation)).append(" ");
        }
        while (!operationsOfExpr.isEmpty()) {
            readyString.append(operationsOfExpr.pop()).append(" ");
        }
        readyString.deleteCharAt(readyString.lastIndexOf("") - 1);
        return readyString.toString();
    }

    private static boolean checkDigits(String number) {
        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        boolean check;
        check = false;
        for (String digit : digits) {
            if (digit == number) {
                check = true;
            }
        }
        return check;
    }

}