package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ExprBuilderImplements implements ExprBuilder {

    private static final char PLUS_SIGN = '+';
    private static final char MINUS_SIGN = '-';
    private static final char MULT_SIGN = '*';
    private static final char DIV_SIGN = '/';
    private static final char POW_SIGN = '^';
    private static final char UNMIN_SIGN = 'm';

    private Deque<Integer> operands = new ArrayDeque<>();
    private Deque<Character> operations = new ArrayDeque<>();
    static Deque<Expr> expression = new ArrayDeque<>();
    private List<Character> list = new ArrayList<>();

    public static void main(String[] args) {
            ExprBuilder exprBuilder = new ExprBuilderImplements();
//            exprBuilder =  exprBuilder.build("m1 - (5 + 5)*2");
            System.out.println(expression.peek());

    }


    @Override
    public ExprBuilder build(@Nullable String input) {
//        Deque<Expr> expression = new ArrayDeque<>();

    }

    public Expr build (String input) {
        String str = ExpressionToPostFix(input);
        String operand = "";

        for (int i = 0; i < str.length();i++) {

            if (str.charAt(i) == ' ') continue;

            if (getP(str.charAt(i)) == 0) {
                while (str.charAt(i) != ' ' && getP(str.charAt(i)) == 0) {
                    operand += str.charAt(i++);
                    if (i == str.length()) break;
                }
                expression.addLast(new Const(Integer.parseInt(operand)));
            }

            if ((getP(str.charAt(i)) > 1) && (getP(str.charAt(i)) < 5)) {

                Expr right = expression.pollFirst();
                Expr left = expression.pollFirst();
                switch (str.charAt(i)) {
                    case PLUS_SIGN: expression.addFirst(new Add(left,right));break;
                    case MINUS_SIGN: expression.addFirst(new Sub(left,right)); break;
                    case MULT_SIGN: expression.addFirst(new Mylt(left,right)); break;
                    case DIV_SIGN: expression.addFirst(new Div(left,right)); break;
                    case POW_SIGN: expression.addFirst(new Pow(left,right)); break;
                    default:
                        break;
                }
            }

            if (getP(str.charAt(i)) == 5) {
                Expr oper = expression.pollFirst();
                expression.addFirst(new UnMin(oper));
            }

        }

        return expression.peekFirst();
    }

    public static String ExpressionToPostFix (String input ) {

        String current = "";
        Deque<Character> stack = new ArrayDeque<>();
        int preority;

        String str = input.replaceAll(" ","");

        for (int i = 0;i < str.length();i++) {
            preority = getP(str.charAt(i));

            if (preority == 0) current += str.charAt(i);
            if (preority == 1) stack.addFirst(str.charAt(i));

            if (preority > 1) {
                current +=' ';

                while(!stack.isEmpty()) {
                    if (getP(stack.peekFirst()) >= preority) {
                        current += stack.pollFirst();
                    }
                    else break;
                }
                stack.addFirst(str.charAt(i));

            }

            if (preority == -1) {

                while(getP(stack.peekFirst()) != 1) {
                    current += stack.pollFirst();
                }
                stack.pollFirst();
            }
        }

        while (!stack.isEmpty()) {
            current += stack.pollFirst();
        }

        return current;
    };


    public static int getP (char token) {
        if (token == 'm') return 5;
        if (token == '^') return 4;
        if (token == '*' || token =='/') return 3;
        if (token == '+' || token =='-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }
}

