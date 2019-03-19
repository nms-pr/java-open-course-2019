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

    private final static int MAX_LENGTH = 100;
    private final static char OPEN_ROUND = '(';
    private final static char OPEN_SQUARE = '[';
    private final static char OPEN_FIGURE = '{';
    private final static char CLOSE_ROUND = ')';
    private final static char CLOSE_SQUARE = ']';
    private final static char CLOSE_FIGURE = '}';
    private static int successCheckers = 0;
    private static int failedCheckers = 0;
    private static String lastSuccessSequence = "";

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
        if (sequence == null || sequence.isEmpty()) {
            lastSuccessSequence = "";
            successCheckers++;
            return true;
        }
        if (sequence.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Length must be less than " + MAX_LENGTH);
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < sequence.length(); i++) {
            if (isOpening(sequence.charAt(i))) {
                stack.push(sequence.charAt(i));
            } else if (isClosing(sequence.charAt(i))) {
                if (stack.isEmpty() || !arePair(stack.pop(), sequence.charAt(i))) {
                    failedCheckers++;
                    return false;
                }
            } else {
                throw new IllegalArgumentException("Invalid sequence, should be brackets only");
            }
        }
        if(!stack.isEmpty()) {
            failedCheckers++;
            return false;
        }
        lastSuccessSequence = sequence;
        successCheckers++;
        return true;

    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return successCheckers;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return failedCheckers;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
    }
    static boolean isOpening(Character character) {
        if (character.equals(OPEN_FIGURE) || character.equals(OPEN_ROUND) || character.equals(OPEN_SQUARE)) {
            return true;
        }
        return false;
    }

    static boolean isClosing(Character character) {
        if (character.equals(CLOSE_FIGURE) || character.equals(CLOSE_ROUND) || character.equals(CLOSE_SQUARE)) {
            return true;
        }
        return false;
    }

    static boolean arePair(Character open, Character close) {
        if (open.equals(OPEN_SQUARE) && close.equals(CLOSE_SQUARE)
                || open.equals(OPEN_ROUND) && close.equals(CLOSE_ROUND)
                || open.equals(OPEN_FIGURE) && close.equals(CLOSE_FIGURE)) {
            return true;
        }
        return false;
    }

}
