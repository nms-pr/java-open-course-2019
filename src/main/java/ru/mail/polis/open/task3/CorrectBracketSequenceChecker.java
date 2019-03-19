package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.*;

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

    private static int successChecksCount = 0;
    private static int failChecksCount = 0;
    private static String lastSuccessSequence = null;
    private static Deque<Character> bracets = new ArrayDeque<>();


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
            failChecksCount++;
            throw new IllegalArgumentException("String equals null.");
        } else if ((sequence != null) && (sequence.isEmpty())) {
            lastSuccessSequence = sequence;
            successChecksCount++;
            return true;
        }
        if (sequence.length() > 100) {
            failChecksCount++;
            throw new IllegalArgumentException("String contains more than 100 characters.");
        }
        if (!sequence.matches("[(){}\\[\\]]+")) {
            failChecksCount++;
            throw new IllegalArgumentException("Invalid string format.");
        }
        for (int i = 0; i < sequence.length(); i++) {
            if ((sequence.charAt(i) == OPEN_ROUND_BRACKET) || (sequence.charAt(i) == OPEN_FIGURE_BRACKET) || (sequence.charAt(i) == OPEN_SQUARE_BRACKET)) {
                bracets.addFirst(sequence.charAt(i));
            } else if (((bracets.peekFirst() == reverseBracket(sequence.charAt(i))))) {
                bracets.removeFirst();
            } else {
                failChecksCount++;
                return false;
            }
        }

        if (bracets.isEmpty()) {
            lastSuccessSequence = sequence;
            successChecksCount++;
            return true;
        }

        failChecksCount++;
        return false;
    }


    public static char reverseBracket(char bracket) {
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


    public static void main(String[] args) {
        Deque<String> list1 = new ArrayDeque<>();
        list1.add("()");
        list1.add("[]");
        list1.add("xx");
        list1.add("");
        list1.add("({}[])");
        list1.add(null);
        for (String elem : list1) {
            checkSequence(elem);
        }
        System.out.println(getSuccessChecksCount());
        System.out.println(getFailChecksCount());
        System.out.println(getLastSuccessSequence());
    }
}
