package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.Objects;


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

    private static final char OPEN_ROUND_BRACKET = '(';
    private static final char CLOSE_ROUND_BRACKET = ')';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char OPEN_FIGURE_BRACKET = '{';
    private static final char CLOSE_FIGURE_BRACKET = '}';

    private static int successChecksCount;
    private static int failChecksCount;
    private static String lastSuccessSequence;

    private static Deque<Character> deque = new ArrayDeque<>();

    public static void setSuccessChecksCount(int successChecksCount) {
        CorrectBracketSequenceChecker.successChecksCount = successChecksCount;
    }

    public static void setFailChecksCount(int failChecksCount) {
        CorrectBracketSequenceChecker.failChecksCount = failChecksCount;
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
        deque.clear();
        try {
            if (Objects.requireNonNull(sequence).isEmpty()) {
                lastSuccessSequence = sequence;
                successChecksCount++;
                return true;
            } else if (sequence.length() > 100 || !sequence.matches("[(){}\\[\\]]+")) {
                failChecksCount++;
                throw new IllegalArgumentException();
            } else {
                for (int i = 0; i < sequence.length(); i++) {
                    if (sequence.charAt(i) == OPEN_ROUND_BRACKET
                            || sequence.charAt(i) == OPEN_FIGURE_BRACKET
                            || sequence.charAt(i) == OPEN_SQUARE_BRACKET) {
                        deque.addFirst(sequence.charAt(i));
                    } else if (((deque.peekFirst() == oppositeBracket(sequence.charAt(i))))) {
                        deque.removeFirst();
                    } else {
                        failChecksCount++;
                        return false;
                    }
                }
            }
        } catch (EmptyStackException | NullPointerException | IllegalArgumentException e) {
            failChecksCount++;
            return false;
        }
        if (deque.isEmpty()) {
            lastSuccessSequence = sequence;
            successChecksCount++;
            return true;
        }
        failChecksCount++;
        return false;
        //throw new UnsupportedOperationException("todo: implement this");
    }

    public static char oppositeBracket(char bracket) {
        if (bracket == CLOSE_FIGURE_BRACKET) {
            return OPEN_FIGURE_BRACKET;
        }
        if (bracket == CLOSE_ROUND_BRACKET) {
            return OPEN_ROUND_BRACKET;
        }
        if (bracket == CLOSE_SQUARE_BRACKET) {
            return OPEN_SQUARE_BRACKET;
        }
        throw new IllegalArgumentException();
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return successChecksCount;
        //throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return failChecksCount;
        //throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
        //throw new UnsupportedOperationException("todo: implement this");
    }

    public static void main(String[] args) {

    }
}