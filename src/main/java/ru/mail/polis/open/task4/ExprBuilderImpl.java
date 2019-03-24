package ru.mail.polis.open.task4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExprBuilderImpl implements ExprBuilder {

    private Deque<Expr> operands;

    public ExprBuilderImpl() {
        operands = new ArrayDeque<>();

    }

    @Override
    public Expr build(String input) {

        List<String> postfixForm = FormBuilder.generatePostfixTokenForm(input);

        operands.clear();

        for (String token : postfixForm) {
            if (Character.isDigit(token.charAt(0))) {
                onOperandFound(token);
            } else {
                onOperationFound(token);
            }
        }

        return operands.pop();
    }

    private void onOperandFound(String operand) {
        operands.push(
            new Const(
                Integer.parseInt(operand)
            )
        );
    }

    /**
     * Creates new operand, corresponding to operation, and pushes it to stack
     *
     * @param operation - symbolic representation of operation to perform
     */
    private void onOperationFound(String operation) {
        switch (operation) {

            case FormBuilder.PLUS_SIGN: {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Add(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case FormBuilder.MINUS_SIGN: {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Subtract(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case FormBuilder.MULTIPLY_SIGN: {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Multiply(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case FormBuilder.DIVIDE_SIGN: {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Divide(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case FormBuilder.POWER_SIGN: {
                Expr operand2 = operands.pop();
                Expr operand1 = operands.pop();
                operands.push(
                    new Power(
                        operand1,
                        operand2
                    )
                );
                break;
            }

            case FormBuilder.UNARY_MINUS_SIGN: {
                operands.push(
                    new UnaryMinus(
                        operands.pop()
                    )
                );
                break;
            }

            default : {
                throw new IllegalArgumentException("No such operation found:" + operation);
            }
        }
    }
}
