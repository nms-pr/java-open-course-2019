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

    private static String lastString;
    private static int checksCount;
    private static int successCounter;
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
        checksCount++;

        if (sequence == null) {
            throw new IllegalArgumentException("This string equals null");
        }

        if (sequence.length() > 100) {
            throw new IllegalArgumentException("String's length is more than 100");
        }


        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case '{':
                case '[':
                case '(':
                    stack.add(sequence.charAt(i));
                    break;

                case '}':
                    if (isNotContains('{')) {
                        return false;
                    }
                    break;

                case ']':
                    if (isNotContains('[')) {
                        return false;
                    }
                    break;

                case ')':
                    if (isNotContains('(')) {
                        return false;
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Found character not bracket.");
            }
        }

        if (stack.isEmpty()) {
            lastString = sequence;
            successCounter++;
            return true;
        } else {
            return false;
        }
    }


    public static boolean isNotContains(char a) {
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
        return successCounter;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return checksCount - successCounter;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastString;
    }

    public static void reset() {
        lastString = null;
        checksCount = 0;
        successCounter = 0;
        stack.clear();
    }
}
