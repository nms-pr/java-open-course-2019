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

    private static final char OPENED_ROUND_BRACKET = '(';
    private static final char CLOSED_ROUND_BRACKET = ')';
    private static final char OPENED_FIGURE_BRACKET = '{';
    private static final char CLOSED_FIGURE_BRACKET = '}';
    private static final char OPENED_SQUARE_BRACKET = '[';
    private static final char CLOSED_SQUARE_BRACKET = ']';

    private static Integer quantityOfSuccessfulAttempts = 0;
    private static Integer quantityOfFailedAttempts = 0;
    private static String lastCorrectSequence;
    private static Deque<Character> dequeChar;
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
        //throw new UnsupportedOperationException("todo: implement this");

        if (sequence == null) {
            quantityOfFailedAttempts++;
            throw new IllegalArgumentException("Вы ничего не ввели.");
        }

        if (sequence.length() > 100) {
            quantityOfFailedAttempts++;
            throw new IllegalArgumentException("Вы ввели последовательность превышающую 100 символов.");
        }

        if (sequence.equals("")) {
            lastCorrectSequence = sequence;
            quantityOfSuccessfulAttempts++;
            return true;
        }

        dequeChar = new ArrayDeque<>();

        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == OPENED_ROUND_BRACKET
                    || sequence.charAt(i) == CLOSED_SQUARE_BRACKET
                    || sequence.charAt(i) == OPENED_SQUARE_BRACKET
                    || sequence.charAt(i) == OPENED_FIGURE_BRACKET
                    || sequence.charAt(i) == CLOSED_ROUND_BRACKET
                    || sequence.charAt(i) == CLOSED_FIGURE_BRACKET ) {
                chekedBracket(sequence.charAt(i));

            } else {
                throw new IllegalArgumentException("Последовательность должна содержать только символы: (){}[]");
            }

        }



        return true;
    }

    private static void chekedBracket( char bracket){
        if (!dequeChar.isEmpty()
                && (( dequeChar.peekFirst().equals(OPENED_ROUND_BRACKET) && bracket == CLOSED_ROUND_BRACKET)
                    || ( dequeChar.peekFirst().equals(OPENED_SQUARE_BRACKET) && bracket == CLOSED_SQUARE_BRACKET)
                    || ( dequeChar.peekFirst().equals(OPENED_FIGURE_BRACKET) && bracket == CLOSED_FIGURE_BRACKET))) {
            dequeChar.pop();
        } else {
            dequeChar.addFirst(bracket);
        }
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        throw new UnsupportedOperationException("todo: implement this");
    }
}
