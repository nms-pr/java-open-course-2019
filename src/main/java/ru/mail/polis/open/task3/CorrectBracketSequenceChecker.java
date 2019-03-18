package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

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

    private static final int MAX_SEQUENCE_LENGTH = 100;
    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char OPEN_BRACE = '{';
    private static final char CLOSE_ROUND_BRACKET = ')';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char CLOSE_BRACE = '}';

    private static String lastSuccessSequence = null;
    private static char[] openingBrackets = new char[MAX_SEQUENCE_LENGTH / 2];
    private static int successChecksCount = 0;
    private static int failChecksCount = 0;

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
            throw new IllegalArgumentException("String equals null.");
        }
        if (sequence == "") {
            successChecksCount++;
            lastSuccessSequence = sequence;
            return true;
        }
        if (sequence.length() > MAX_SEQUENCE_LENGTH) {
            throw new IllegalArgumentException("String contains more than 100 characters.");
        }

        int index = -1;

        for (int i = 0; i < sequence.length(); i++) {
            if ((sequence.charAt(i) == OPEN_ROUND_BRACKET)
                    || (sequence.charAt(i) == OPEN_SQUARE_BRACKET)
                    || (sequence.charAt(i) == OPEN_BRACE)) {
                index++;
                if (index > openingBrackets.length - 1) {
                    failChecksCount++;
                    return false;
                }
                openingBrackets[index] = sequence.charAt(i);
            } else if ((sequence.charAt(i) == CLOSE_ROUND_BRACKET)
                    || (sequence.charAt(i) == CLOSE_SQUARE_BRACKET)
                    || (sequence.charAt(i) == CLOSE_BRACE)) {
                if (index < 0) {
                    failChecksCount++;
                    return false;
                }
                if (bracketsArePaired(openingBrackets[index], sequence.charAt(i))) {
                    index--;
                } else {
                    failChecksCount++;
                    return false;
                }
            } else {
                throw new IllegalArgumentException("String contains non-bracket character.");
            }

        }

        if (index == -1) {
            successChecksCount++;
            lastSuccessSequence = sequence;
            return true;
        } else {
            failChecksCount++;
            return false;
        }
    }

    private static boolean bracketsArePaired(char openBracket, char closeBracket) {
        if ((openBracket == OPEN_ROUND_BRACKET) && (closeBracket == CLOSE_ROUND_BRACKET)) {
            return true;
        }
        if ((openBracket == OPEN_SQUARE_BRACKET) && (closeBracket == CLOSE_SQUARE_BRACKET)) {
            return true;
        }
        if ((openBracket == OPEN_BRACE) && (closeBracket == CLOSE_BRACE)) {
            return true;
        }
        return false;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return successChecksCount;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return failChecksCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
    }
}
