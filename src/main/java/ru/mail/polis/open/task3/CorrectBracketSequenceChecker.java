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
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char OPEN_FIGURE_BRACKET = '{';
    private static final char CLOSE_FIGURE_BRACKET = '}';
    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char CLOSE_ROUND_BRACKET = ')';

    private static int numberOfFailedAttempts = 0;
    private static int numberOfSuccessfulAttempts = 0;
    private static String lastCorrectSequence = null;
    private static Deque<Character> turnOfBrackets = new ArrayDeque<>();

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
        if (sequence.length() > 100) {
            sequence = null;
            turnOfBrackets.clear();
            throw new IllegalArgumentException("String should not be longer than 100 characters");
        } else if (sequence.equals("")) {
            turnOfBrackets.clear();
            lastCorrectSequence = sequence;
            sequence = null;
            numberOfSuccessfulAttempts++;
            return true;
        } else {
            for (int i = 0; i < sequence.length(); i++) {
                if (sequence.charAt(i) == OPEN_FIGURE_BRACKET
                        || sequence.charAt(i) == OPEN_ROUND_BRACKET
                        || sequence.charAt(i) == OPEN_SQUARE_BRACKET) {
                    turnOfBrackets.addLast(sequence.charAt(i));
                } else {
                    switch (sequence.charAt(i)) {
                        case CLOSE_FIGURE_BRACKET:
                            if ((turnOfBrackets.isEmpty()) || (turnOfBrackets.peekLast() != OPEN_FIGURE_BRACKET)) {
                                numberOfFailedAttempts++;
                                turnOfBrackets.clear();
                                sequence = null;
                                return false;
                            } else {
                                turnOfBrackets.pollLast();
                            }
                            break;
                        case CLOSE_ROUND_BRACKET:
                            if ((turnOfBrackets.isEmpty()) || (turnOfBrackets.getLast() != OPEN_ROUND_BRACKET)) {
                                numberOfFailedAttempts++;
                                turnOfBrackets.clear();
                                sequence = null;
                                return false;
                            } else {
                                turnOfBrackets.pollLast();
                            }
                            break;
                        case CLOSE_SQUARE_BRACKET:
                            if ((turnOfBrackets.isEmpty()) || (turnOfBrackets.getLast() != OPEN_SQUARE_BRACKET)) {
                                numberOfFailedAttempts++;
                                turnOfBrackets.clear();
                                sequence = null;
                                return false;
                            } else {
                                turnOfBrackets.pollLast();
                            }
                            break;
                        default:
                            numberOfFailedAttempts++;
                            turnOfBrackets.clear();
                            sequence = null;
                            throw new IllegalArgumentException("Wrong symbols in the sequence.");
                    }
                }
            }
        }
        if (!turnOfBrackets.isEmpty()) {
            numberOfFailedAttempts++;
            turnOfBrackets.clear();
            sequence = null;
            return false;
        } else {
            numberOfSuccessfulAttempts++;
            lastCorrectSequence = sequence;
            turnOfBrackets.clear();
            sequence = null;
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
        return numberOfSuccessfulAttempts;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return numberOfFailedAttempts;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */

    public static @Nullable String getLastSuccessSequence() {
        return lastCorrectSequence;
    }

    public static void reset() {
        lastCorrectSequence = null;
        numberOfSuccessfulAttempts = 0;
        numberOfFailedAttempts = 0;
        turnOfBrackets.clear();
    }
}
