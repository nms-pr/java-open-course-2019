package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

/**
 * Реализовать НЕ обратной польской записью,
 * а Рекурсивным спуском
 */
public class ExprBuilderImpl implements ExprBuilder {

    @Override
    public Expr build(@Nullable String input) {

        Const aConst = new Const(2);
        return aConst;
    }
}
