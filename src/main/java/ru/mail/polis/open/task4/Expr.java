package ru.mail.polis.open.task4;

@FunctionalInterface
public interface Expr {

    /**
     * Вычисляет значение выражение.
     * <p>
     * Вычисление должно производиться лениво.
     * <p>
     * Необходимо реализовать все операции, описанные в {@link ExprBuilder}.
     *
     * @return результат вычисления выражение
     */
    int evaluate();
}
