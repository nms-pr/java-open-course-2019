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
    private static final char OPEN_CURLY_BRACKET = '{';
    private static final char CLOSE_CURLY_BRACKET = '}';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    private static int successChecksCount = 0;
    private static int failChecksCount = 0;
    private static String lastSuccessSequence;
    private static Deque<Character> stack = new ArrayDeque<>();

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

        stack.clear();

        if (sequence == null) {
            failChecksCount++;
            throw new IllegalArgumentException("String should not be null.");
        }

        if (sequence.length() > 100) {
            failChecksCount++;
            throw new IllegalArgumentException("String must be no more than 100 characters.");
        }

        for (int i = 0; i < sequence.length(); i++) {

            if (sequence.charAt(i) == OPEN_BRACKET
                    || sequence.charAt(i) == CLOSE_BRACKET
                    || sequence.charAt(i) == OPEN_SQUARE_BRACKET
                    || sequence.charAt(i) == CLOSE_SQUARE_BRACKET
                    || sequence.charAt(i) == OPEN_CURLY_BRACKET
                    || sequence.charAt(i) == CLOSE_CURLY_BRACKET) {

                bracketHandling(sequence.charAt(i));

            } else {
                throw new IllegalArgumentException("Sequence should contain only the following brackets: "
                        + "'(', ')', '[', ']', '{', '}'.");
            }
        }

        if (stack.size() == 0) {
            lastSuccessSequence = sequence;
            successChecksCount++;
            return true;
        } else {
            failChecksCount++;
            return false;
        }

    }

    /**
     * Если скобка на вершине стека образует правильную скобочную последовательность
     *  со скобкой, получаемой в качестве параметра, то элемент вершины стека удаляется
     *
     * @param bracket - очередная скобка из входной строки
     */
    private static void bracketHandling(char bracket) {

        Character firstStackElement = stack.peekFirst();

        if (firstStackElement == null) {
            stack.addFirst(bracket);
        } else if ((firstStackElement.equals(OPEN_BRACKET) && bracket == CLOSE_BRACKET)
                || (firstStackElement.equals(OPEN_CURLY_BRACKET) && bracket == CLOSE_CURLY_BRACKET)
                || (firstStackElement.equals(OPEN_SQUARE_BRACKET) && bracket == CLOSE_SQUARE_BRACKET)) {
            stack.pollFirst();
        }

    }

    public static void clearParameters() {
        successChecksCount = 0;
        failChecksCount = 0;
        lastSuccessSequence = null;
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
