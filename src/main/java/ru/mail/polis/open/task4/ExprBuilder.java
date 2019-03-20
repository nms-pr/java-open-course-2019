package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface ExprBuilder {

    /**
     * Метод собирающий из входной строки объект являющийся арифметическим выражением.
     * <p>
     * В выражении может быть:
     * умножение - '*'
     * деление - '/'
     * сложение — '+'
     * вычитание — '—'
     * унарный минус — '-'
     * степень — '^'
     * круглые скобки — '(', ')'
     * константы
     * пробелы — их следует игнорировать
     * <p>
     * Все вычисления производятся в типе - int.
     * Приоритет операций аналогичный математическому.
     * <p>
     * Примеры:
     * "5 + 5" = 10 -> Add(Const(5),Const(5))
     * "5 * 2 - 1 + 2" = 11 -> Add( Sub( Mult( Const(5),Const(2) ), Const(1)), Const(2))
     *
     * @param input — входная строка
     * @return арифметическое выражение
     * @throws IllegalArgumentException если арифметическое выражение некорректно
     */
    Expr build(@Nullable String input);
}
