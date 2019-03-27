package ru.mail.polis.open.task4;

import org.jetbrains.annotations.Nullable;

public class ExprParser implements ExprBuilder {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';
    private static final char ADD = '+';
    private static final char SUB = '—';
    private static final char MULT = '*';
    private static final char DIV = '/';
    private static final char POW = '^';
    private static final char MINUS = '-';
    private int bracketsCounter = 0;
    private char[] tokensArray;

    /**
     * Принимает строку, заменяет пробелы пустой строкой
     * После вызывает метод начала рекурсивного спуска
     *
     * @param input - арифметическое выражение
     * @return переход в начало рекурсивного спуска
     * @throws IllegalArgumentException если аргумент null
     */
    @Override
    public Expr build(@Nullable String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        } else {
            input = input.replace(" ", "");
            return getExpression(input);
        }
    }

    /**
     * Принимает строку, проверяет на пустоту, передает строку в метод, обрабатывающий скобки
     * и дальше по спуску в операцию с наименьшим приоритетом
     *
     * @param tokens - арифметическое выражение
     * @return переход в метод обработки сложения и вычитания
     * @throws IllegalArgumentException если строка пустая
     */
    private Expr getExpression(String tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException();
        }
        tokens = handleBrackets(tokens);

        return addSub(tokens);
    }

    /**
     * Принимает строку, делит на 2 части по операциям сложения или вычитания (если они есть),
     * вызывает спуск с самого начала рекурсивно для обеих частей
     *
     * @param tokens - арифметическое выражение
     * @return переход в метод обработки деления и умножения
     */
    private Expr addSub(String tokens) {
        tokensArray = tokens.toCharArray();
        for (int counter = 0; counter < tokensArray.length; counter++) {
            switch (tokensArray[counter]) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case ADD:
                    if (bracketsCounter == 0) {
                        return new Add(
                            getExpression(
                                tokens.substring(0, counter)),
                            getExpression(
                                tokens.substring(counter + 1)));
                    } else {
                        break;
                    }
                case SUB:
                    if (bracketsCounter == 0) {
                        return new Sub(
                            getExpression(
                                tokens.substring(0, counter)),
                            getExpression(
                                tokens.substring(counter + 1)));
                    } else {
                        break;
                    }
                default:
                    break;
            }

        }
        return divMult(tokens);
    }

    /**
     * Принимает строку, делит на 2 части по операциям деления и умножения (если они есть),
     * вызывает спуск с самого начала рекурсивно для обеих частей
     *
     * @param tokens - арифметическое выражение
     * @return переход в метод обработки операции возведения в степень
     */
    private Expr divMult(String tokens) {
        tokensArray = tokens.toCharArray();
        for (int counter = 0; counter < tokensArray.length; counter++) {
            switch (tokensArray[counter]) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case DIV:
                    if (bracketsCounter == 0) {
                        return new Div(
                            getExpression(
                                tokens.substring(0, counter)),
                            getExpression(
                                tokens.substring(counter + 1)));
                    } else {
                        break;
                    }
                case MULT:
                    if (bracketsCounter == 0) {
                        return new Mult(
                            getExpression(
                                tokens.substring(0, counter)),
                            getExpression(
                                tokens.substring(counter + 1)));
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
        return pow(tokens);
    }

    /**
     * Принимает строку, делит на 2 части по операции возведения в степень (если она есть),
     * вызывает спуск с самого начала рекурсивно для обеих частей
     *
     * @param tokens - арифметическое выражение
     * @return переход в метод обработки выражения с унарным минусом
     */
    private Expr pow(String tokens) {
        tokensArray = tokens.toCharArray();
        for (int counter = 0; counter < tokensArray.length; counter++) {
            switch (tokensArray[counter]) {
                case OPEN_BRACKET:
                    bracketsCounter++;
                    break;
                case CLOSE_BRACKET:
                    bracketsCounter--;
                    break;
                case POW:
                    if (bracketsCounter == 0) {
                        return new Pow(
                            getExpression(
                                tokens.substring(0, counter)),
                            getExpression(
                                tokens.substring(counter + 1)));
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
        return minus(tokens);
    }

    /**
     * Принимает строку, если она начинается со знака унарного минуса, возвращает
     * выражение класса Minus, в противном случае вызывает метод обработки констант
     *
     * @param tokens - арифметическое выражение
     * @return переход в метод обработки констант
     */
    private Expr minus(String tokens) {
        if (tokens.startsWith(String.valueOf(MINUS))) {
            return new Minus(Integer.valueOf(tokens.substring(1)));
        } else {
            return constant(tokens);
        }
    }

    /**
     * Принимает строку, проверяет на численность и возвращает экземпляр класса Const
     *
     * @param tokens - числовая строка
     * @return экземпляр класса Const
     * @throws IllegalArgumentException если строка не является числовой
     */
    private Expr constant(String tokens) {
        int constant;
        try {
            constant = Integer.parseInt(tokens);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return new Const(constant);
    }

    /**
     * Метод обработки скобок
     * Удаляет скобки в начале и конце выражения, если оно удовлетворяет условиям:
     * 1. скобки находятся только в начале и конце
     * 2. выражение имеет вложенные скобочные последовательности
     * В иных случаях строка не изменяется
     *
     * @param tokens - арифметическое выражение
     * @return преобразованное арифметическое выражение
     */
    String handleBrackets(String tokens) {
        if (tokens.startsWith(String.valueOf(OPEN_BRACKET))
            && tokens.endsWith(String.valueOf(CLOSE_BRACKET))) {
            bracketsCounter = 0;
            tokensArray = tokens.toCharArray();
            loop:
            for (int counter = 0; counter < tokens.length(); counter++) {
                switch (tokensArray[counter]) {
                    case OPEN_BRACKET:
                        bracketsCounter++;
                        break;
                    case CLOSE_BRACKET:
                        bracketsCounter--;
                        if (bracketsCounter == 0) {
                            if (counter == tokensArray.length - 1) {
                                tokens = tokens.substring(1, tokens.length() - 1);
                            }
                            break loop;
                        }
                        break;
                    default:
                        break;
                }
            }
            bracketsCounter = 0;
        }
        return tokens;
    }
}
