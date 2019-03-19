package ru.mail.polis.open.task3;

import java.util.ArrayDeque;
import java.util.Deque;

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

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char CLOSE_ROUND_BRACKET = ')';
    private static final char OPEN_FIGURE_BRACKET = '{';
    private static final char CLOSE_FIGURE_BRACKET = '}';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';

    private static int quantityOfCorrectChecks = 0;
    private static int quantityOfWrongChecks = 0;
    private static String lastCorrectSequence;
    private static Deque<Character> bracketsDeque = new ArrayDeque<>();

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

        bracketsDeque.clear();

        if (sequence == null) {
            quantityOfCorrectChecks++;
            return true;
        }

        if (sequence.length() > 100) {
            quantityOfWrongChecks++;
            throw new IllegalArgumentException("Invalid argument!! The string length is more than 100!");
        }

        for (int i = 0; i < sequence.length(); i++) {

            if ((sequence.charAt(i) == OPEN_ROUND_BRACKET)
                    || (sequence.charAt(i) == OPEN_FIGURE_BRACKET)
                    || (sequence.charAt(i) == OPEN_SQUARE_BRACKET)) {

                bracketsDeque.push(sequence.charAt(i));
            } else if ((sequence.charAt(i) == CLOSE_ROUND_BRACKET)
                    || (sequence.charAt(i) == CLOSE_FIGURE_BRACKET)
                    || (sequence.charAt(i) == CLOSE_SQUARE_BRACKET)) {

                if (bracketsDeque.size() != 0) {

                    switch (sequence.charAt(i)) {
                        case CLOSE_ROUND_BRACKET: {

                            if (bracketsDeque.pop() != OPEN_ROUND_BRACKET) {

                                quantityOfWrongChecks++;
                                return false;
                            }
                            break;
                        }
                        case CLOSE_SQUARE_BRACKET: {

                            if (bracketsDeque.pop() != OPEN_SQUARE_BRACKET) {

                                quantityOfWrongChecks++;
                                return false;
                            }
                            break;
                        }
                        case CLOSE_FIGURE_BRACKET: {

                            if (bracketsDeque.pop() != OPEN_FIGURE_BRACKET) {

                                quantityOfWrongChecks++;
                                return false;
                            }
                            break;
                        }
                        default: {

                            quantityOfWrongChecks++;
                            break;
                        }
                    }
                } else {

                    quantityOfWrongChecks++;
                    return false;
                }
            } else {

                quantityOfWrongChecks++;
                throw new IllegalArgumentException("Invalid argument!! This symbol isn't bracket: "
                        + sequence.charAt(i));
            }

        }

        if (bracketsDeque.size() != 0) {
            quantityOfWrongChecks++;
            return false;
        }

        quantityOfCorrectChecks++;
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
        return quantityOfCorrectChecks;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return quantityOfWrongChecks;
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
        quantityOfCorrectChecks = 0;
        quantityOfWrongChecks = 0;
        lastCorrectSequence = null;
    }
}
