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

    private static final char FIGURE_BRACKET_OPENED = '{';
    private static final char FIGURE_BRACKET_CLOSED = '}';
    private static final char SQUARE_BRACKET_OPENED = '[';
    private static final char SQUARE_BRACKET_CLOSED = ']';
    private static final char ROUND_BRACKET_OPENED = '(';
    private static final char ROUND_BRACKET_CLOSED = ')';

    private static int successChecksCount = 0;
    private static int allChecksCount = 0;
    private static String lastLine = null;

    private static Deque<Character> stack = new ArrayDeque<>(50);

    CorrectBracketSequenceChecker() {
        successChecksCount = 0;
        allChecksCount = 0;
        lastLine = null;
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
        stack.clear();

        allChecksCount++;

        if (sequence == null) {
            throw new IllegalArgumentException("sequence is null");
        }

        if (sequence.length() > 100) {
            throw new IllegalArgumentException("Line is longer than 100 symbols");
        }

        char[] arraySequence = sequence.toCharArray();
        for (int counter = 0; counter < sequence.length(); counter++) {
            switch (arraySequence[counter]) {
                case (FIGURE_BRACKET_OPENED):
                case (SQUARE_BRACKET_OPENED):
                case (ROUND_BRACKET_OPENED):
                    stack.add(arraySequence[counter]);
                    break;
                case (FIGURE_BRACKET_CLOSED):
                    if (isIncorrectClosing(FIGURE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;
                case (SQUARE_BRACKET_CLOSED):
                    if (isIncorrectClosing(SQUARE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;
                case (ROUND_BRACKET_CLOSED):
                    if (isIncorrectClosing(ROUND_BRACKET_OPENED)) {
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("There is incorrect symbol in sequence");
            }
        }
        if (stack.isEmpty()) {
            successChecksCount++;
            lastLine = sequence;
            return true;
        } else {
            return false;
        }
    }

    private static boolean isIncorrectClosing(char left) {
        try {
            if (left == stack.getLast()) {
                stack.removeLast();
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return true;
        }
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
        return allChecksCount - successChecksCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastLine;
    }
}
