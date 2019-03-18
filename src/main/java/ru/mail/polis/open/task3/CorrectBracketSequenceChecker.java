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

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static final char FIRST_PARENTHESIS = '(';
    private static final char LAST_PARENTHESIS = ')';
    private static final char FIRST_SQUARE_BRACKET = '[';
    private static final char LAST_SQUARE_BRACKET = ']';
    private static final char FIRST_FIGURED_BRACKET = '{';
    private static final char LAST_FIGURED_BRACKET = '}';

    private static int amountOfSuccessfulAttemps = 0;
    private static int amountOfFailedAttemps = 0;

    private static String lastCorrectSequence;
    private static Deque<Character> bracketDeque = new ArrayDeque<>();
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
            throw new IllegalArgumentException("The string cannot be null.");
        }
        if (sequence.length() > 100) {
            throw new IllegalArgumentException("The string cannot be longer than 100 characters.");
        }

        if (sequence.equals("")) {
            lastCorrectSequence = sequence;
            amountOfSuccessfulAttemps++;
            return true;
        }

        for (int i = 0; i < sequence.length(); i++) {
            if ((sequence.charAt(i) != FIRST_PARENTHESIS)
                    && (sequence.charAt(i) != LAST_PARENTHESIS)
                    && (sequence.charAt(i) != FIRST_SQUARE_BRACKET)
                    && (sequence.charAt(i) != LAST_SQUARE_BRACKET)
                    && (sequence.charAt(i) != FIRST_FIGURED_BRACKET)
                    && (sequence.charAt(i) != LAST_FIGURED_BRACKET)) {

                amountOfFailedAttemps++;
                throw new IllegalArgumentException("Unknown character : " + sequence.charAt(i));
            }

            if ((sequence.charAt(i) == FIRST_PARENTHESIS)
                    || (sequence.charAt(i) == FIRST_SQUARE_BRACKET)
                    || (sequence.charAt(i) == FIRST_FIGURED_BRACKET)) {

                bracketDeque.push(sequence.charAt(i));
            } else if (bracketDeque.size() != 0) {
                char head = bracketDeque.pop();

                switch (sequence.charAt(i)) {
                    case LAST_PARENTHESIS : {
                        if (head != FIRST_PARENTHESIS) {
                            amountOfFailedAttemps++;
                            return false;
                        }
                        break;
                    }
                    case LAST_FIGURED_BRACKET : {
                        if (head != FIRST_FIGURED_BRACKET) {
                            amountOfFailedAttemps++;
                            return false;
                        }
                        break;
                    }
                    case LAST_SQUARE_BRACKET : {
                        if (head != FIRST_SQUARE_BRACKET) {
                            amountOfFailedAttemps++;
                            return false;
                        }
                        break;
                    }
                    default : {
                        amountOfFailedAttemps++;
                        throw new IllegalArgumentException("Something wrong");
                    }
                }
            } else {
                amountOfFailedAttemps++;
                return false;
            }
        }

        if (bracketDeque.size() != 0) {
            amountOfFailedAttemps++;
            return false;
        }

        amountOfSuccessfulAttemps++;
        lastCorrectSequence = sequence;
        return true;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return amountOfSuccessfulAttemps;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return amountOfFailedAttemps;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return amountOfSuccessfulAttemps != 0 ? lastCorrectSequence : null;
    }

    public static void discard() {
        amountOfSuccessfulAttemps = 0;
        amountOfFailedAttemps = 0;
        lastCorrectSequence = null;
    }
}
