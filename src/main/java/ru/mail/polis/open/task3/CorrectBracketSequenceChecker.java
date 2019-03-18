package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Для проверки класса на корректность следует использовать тесты.
 * Команда {@code ./gradlew clean build} должна завершаться корректно.
 * <p>
 * При решении задания следует обратить внимание на использование ключевых слов {@code final} и {@code static}
 * <p>
 * В результирующим PR нужно предоставить
 * - Код решения
 * Реализовать все методы
 * Весь код внутри CorrectBracketSequencePredicate
 * Сигнатуры класса, конструктора и метода следует оставить неизменными
 * Комментарии оставить неизменными
 * Можно добавлять дополнительные методы
 * - Тесты
 * Внутри package ru.mail.polis.open.task3
 * В нём будут видны public / protected / package_private методы
 */
public final class CorrectBracketSequenceChecker {

    /**
     * Класс, который хранит статистику. Добавил для того, чтобы если внешней программе потребуетс вся статистика,
     * то можно отдать ее сразу одним экземпляром
     *
     */
    private static class StatisticsKeeper {


        private int successChecksCount;
        private int failChecksCount;
        private String lastSuccessSequence;

        private StatisticsKeeper() {
            successChecksCount = 0;
            failChecksCount = 0;
            lastSuccessSequence = null;
        }

    }

    private static final char OPEN_CURLY_BRACKET = '{';
    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_CURLY_BRACKET = '}';
    private static final char CLOSE_ROUND_BRACKET = ')';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final int LENGTH_LIMIT = 100;

    private static final Deque<Character> charStack;
    private static StatisticsKeeper statisticsKeeper;

    static {

        statisticsKeeper = new StatisticsKeeper();
        charStack = new ArrayDeque<>();
    }

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    /**
     * Метод проверяющий скобочную последовательность на правильность.
     * <p>
     * Пустая строка
     * — правильная скобочная последовательность.
     * Правильная скобочная последовательность, взятая в скобки одного типа
     * — правильная скобочная последовательность.
     * Правильная скобочная последовательность,
     * к которой слева или справа приписана правильная скобочная последовательность
     * — правильная скобочная последовательность.
     * <p>
     * Последовательности из больше чем ста символов или с символами не скобок — некорректные.
     * <p>
     * Скобки бывают:
     * 1. Круглые '(', ')'
     * 2. Квадратные '[', ']'
     * 3. Фигурные '{', '}'
     *
     * @param sequence — входная строка
     * @return {@code true} — если скобочная последовательность корректна и {@code false} иначе
     * @throws IllegalArgumentException если в строке содержатся символы, не являющиеся скобками
     *                                  или если входная строка содержит больше ста символов
     */
    public static boolean checkSequence(@Nullable String sequence) {

        if (sequence == null) {

            onSuccessfulCheck(null);
            return true;
        }

        if (sequence.length() > LENGTH_LIMIT) {

            throw new IllegalArgumentException("Sequence is more than 100 characters long");
        }

        charStack.clear();

        for (int i = 0; i < sequence.length(); i++) {

            if (isOpenBracket(sequence.charAt(i))) {

                charStack.push(sequence.charAt(i));
                continue;
            }

            if (isCloseBracket(sequence.charAt(i))) {

                if (charStack.isEmpty()) {

                    statisticsKeeper.failChecksCount++;
                    return false;
                }

                if (!charCorrespondsToCharInStack(sequence.charAt(i), charStack.pop())) {

                    statisticsKeeper.failChecksCount++;
                    return false;
                }
                continue;
            }

            statisticsKeeper.failChecksCount++;
            throw new IllegalArgumentException("Sequence contains non-bracket character");
        }

        if (charStack.isEmpty()) {

            onSuccessfulCheck(sequence);
            return true;
        } else {

            statisticsKeeper.failChecksCount++;
            return false;
        }
    }

    private static void onSuccessfulCheck(String sequence) {

        statisticsKeeper.successChecksCount++;
        statisticsKeeper.lastSuccessSequence = sequence;
    }

    private static boolean charCorrespondsToCharInStack(char sequenceChar, char stackChar) {

        switch (stackChar) {
            case OPEN_CURLY_BRACKET: {
                return sequenceChar == CLOSE_CURLY_BRACKET;
            }

            case OPEN_ROUND_BRACKET: {
                return sequenceChar == CLOSE_ROUND_BRACKET;
            }

            case OPEN_SQUARE_BRACKET: {
                return sequenceChar == CLOSE_SQUARE_BRACKET;
            }

            default: {
                throw new IllegalArgumentException("Stack char is not an open bracket");
            }
        }
    }

    private static boolean isCloseBracket(char c) {

        return (c == CLOSE_ROUND_BRACKET)
                    || (c == CLOSE_CURLY_BRACKET)
                    || (c == CLOSE_SQUARE_BRACKET);
    }

    private static boolean isOpenBracket(char c) {

        return (c == OPEN_ROUND_BRACKET)
                    || (c == OPEN_CURLY_BRACKET)
                    || (c == OPEN_SQUARE_BRACKET);
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {

        return statisticsKeeper.successChecksCount;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {

        return statisticsKeeper.failChecksCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {

        return statisticsKeeper.lastSuccessSequence;
    }

    public static void clearAll() {

        statisticsKeeper = new StatisticsKeeper();
    }
}
