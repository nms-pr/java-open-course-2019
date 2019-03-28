package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ExprBuilderImplements implements ExprBuilder {

    private static final char PLUS_SIGN = '+';
    private static final char MINUS_SIGN = '-';
    private static final char MULT_SIGN = '*';
    private static final char DIV_SIGN = '/';
    private static final char POW_SIGN = '^';
    private static final char UNMIN_SIGN = 'm';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static Deque<Expr> expression =  new ArrayDeque<>();

    @Override
    public Expr build(String input) {

        expression.clear();
        StringBuilder stringBuilder = expressionToPostFix(input);
        String str = String.valueOf(stringBuilder);
        StringTokenizer st = new StringTokenizer(str, " ");

        while (st.hasMoreTokens()) {

            String token = st.nextToken();

            if (getPriority(token.charAt(0)) == 0) {
                int operand = Integer.parseInt(token);
                expression.push(new Const(operand));
            } else {

                if (expression.isEmpty()) {
                    throw new IllegalArgumentException("Systax error");
                }
                Expr right = expression.pop();

                if (token.charAt(0) == UNMIN_SIGN) {
                    expression.push(new UnMin(right));
                } else {

                    if (expression.isEmpty()) {
                        throw new IllegalArgumentException("Systax error");
                    }
                    Expr left = expression.pop();

                    switch (token.charAt(0)) {
                        case PLUS_SIGN:
                            expression.addFirst(new Add(left, right));
                            break;
                        case MINUS_SIGN:
                            expression.addFirst(new Sub(left, right));
                            break;
                        case MULT_SIGN:
                            expression.addFirst(new Mylt(left, right));
                            break;
                        case DIV_SIGN:
                            expression.addFirst(new Div(left, right));
                            break;
                        case POW_SIGN:
                            expression.addFirst(new Pow(left, right));
                            break;
                        default:
                            throw new IllegalArgumentException("Error in data");
                    }
                }
            }
        }

        if (expression.size() != 1) {
            throw new IllegalArgumentException("Systax error");
        }

        return expression.peekFirst();
    }

    /*
    @param входное выражение
    @return постфиксная форма выражения, в которой операнды и операции разделены пробелами
    если в выражении присудствует m,
    то операнд до него сдвигается на один пробел больше
    На подсчёт это не влияет
     */
    public static StringBuilder expressionToPostFix(String input) {

        if (input == null) {
            throw  new IllegalArgumentException("Input is null");
        }
        StringBuilder currentBuilder  = new StringBuilder("");
        String current = "";
        Deque<Character> stack = new ArrayDeque<>();
        int preority;

        String str = input.replaceAll(" ","");

        if (input == "") {
            throw  new IllegalArgumentException("Input nothing ");
        }

        for (int i = 0; i < str.length(); i++) {
            preority = getPriority(str.charAt(i));

            if (preority == 0) {
                currentBuilder.append(str.charAt(i));
                current += str.charAt(i);
            } else if (preority == 1) {
                stack.addFirst(str.charAt(i));
            } else if (preority > 1) {
                currentBuilder.append(' ');
                current += ' ';
                while (!stack.isEmpty()) {
                    if (getPriority(stack.peekFirst()) >= preority) {
                        currentBuilder.append(stack.pollFirst());
                        current += stack.pollFirst();
                    } else {
                        break;
                    }
                    currentBuilder.append(' ');
                    current += ' ';
                }
                stack.addFirst(str.charAt(i));
            } else if (preority == -1) {

                while (!stack.isEmpty() && getPriority(stack.peekFirst()) != 1) {
                    currentBuilder.append(' ');
                    current += ' ';
                    currentBuilder.append(stack.pollFirst());
                    current += stack.pollFirst();
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException(" not left bracket");
                } else {
                    stack.pollFirst();
                }
            }
        }

        while (!stack.isEmpty()) {
            if (getPriority(stack.peekFirst()) == 1) {
                throw new IllegalArgumentException(" Error");
            }
            currentBuilder.append(' ');
            current += ' ';
            currentBuilder.append(stack.pollFirst());
            current += stack.pollFirst();
        }

        return currentBuilder;
    }

    /*
    @param символ из входной строки
    @return приоритет этого символа
    @throws IllegalArgumentException если встречен неизвестный символ
     */
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
        if (token == UNMIN_SIGN) {
            return 5;
        }
        if (token == POW_SIGN) {
            return 4;
        }
        if (token == MULT_SIGN || token == DIV_SIGN) {
            return 3;
        }
        if (token == PLUS_SIGN || token == MINUS_SIGN) {
            return 2;
        }
        if (token == OPEN_BRACKET) {
            return 1;
        }
        if (token == CLOSE_BRACKET) {
            return -1;
        } else {
            throw new IllegalArgumentException("No correct char");
        }
    }
}

