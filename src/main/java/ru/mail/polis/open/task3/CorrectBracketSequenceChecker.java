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

    private static String last_str;
    private static int check_count;
    private static int success_counter;
    private static Deque<Character> stack = new ArrayDeque<>();

    private static final char ROUND_BRACKET_OPENED = '(';
    private static final char ROUND_BRACKET_CLOSED = ')';

    private static final char SQUARE_BRACKET_OPENED = '[';
    private static final char SQUARE_BRACKET_CLOSED = ']';

    private static final char FIGURE_BRACKET_OPENED = '{';
    private static final char FIGURE_BRACKET_CLOSED = '}';


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
        check_count++;

        if (sequence == null) {
            throw new IllegalArgumentException("This string equals null");
        }

        if (sequence.length() > 100) {
            throw new IllegalArgumentException("String's length is more than 100");
        }


        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case (FIGURE_BRACKET_OPENED):
                case (SQUARE_BRACKET_OPENED):
                case (ROUND_BRACKET_OPENED):
                    stack.add(sequence.charAt(i));
                    break;

                case (FIGURE_BRACKET_CLOSED):
                    if (notinclude(FIGURE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                case (SQUARE_BRACKET_CLOSED):
                    if (notinclude(SQUARE_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                case (ROUND_BRACKET_CLOSED):
                    if (notinclude(ROUND_BRACKET_OPENED)) {
                        return false;
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Found character not bracket.");
            }
        }

        if (stack.isEmpty()) {
            last_str = sequence;
            success_counter++;
            return true;
        } else {
            return false;
        }
    }


    public static boolean notinclude(char a) {
        try {
            if (a == stack.getLast()) {
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
        return success_counter;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return check_count - success_counter;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return last_str;
    }

    public static void reset() {
        last_str = null;
        check_count = 0;
        success_counter = 0;
        stack.clear();
    }
}