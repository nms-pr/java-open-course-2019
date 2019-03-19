package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;
import java.util.Deque;
import java.util.ArrayDeque;

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
    private static int successChecksCount = 0;
    private static int failChecksCount = 0;
    private static String lastSuccessSequence = null;

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
        if (sequence == null || sequence.length() == 0) {
            lastSuccessSequence = sequence;
            successChecksCount++;
            return true;
        }
        if (sequence.length() > 100) {
            failChecksCount++;
            throw new IllegalArgumentException();
        }
        boolean checkBrackets = false;
        Deque<Character> bracketDeque = new ArrayDeque<>();
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == '(' || sequence.charAt(i) == '[' || sequence.charAt(i) == '{') {
                bracketDeque.add(sequence.charAt(i));
            } else {
                if (sequence.charAt(i) == ')' || sequence.charAt(i) == ']' || sequence.charAt(i) == '}') {
                    if (sequence.charAt(i) == ')' && bracketDeque.pollLast() == '('
                            || sequence.charAt(i) == ']' && bracketDeque.pollLast() == '['
                            || sequence.charAt(i) == '}' && bracketDeque.pollLast() == '{') {
                        checkBrackets = true;
                    } else {
                        checkBrackets = false;
                        break;
                    }
                } else {
                    failChecksCount++;
                    throw new IllegalArgumentException();
                }
            }
        }
        if (checkBrackets) {
            successChecksCount++;
            lastSuccessSequence = sequence;
        } else {
            failChecksCount++;
        }
        return checkBrackets;
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

    /**
     * Обнуляет количество удачных проверок
     */
    public static void resetSuccessChecksCount() {
        successChecksCount = 0;
    }

    /**
     * Обнуляет количество неудачных проверок
     */
    public static void resetFailChecksCount() {
        failChecksCount = 0;
    }

    /**
     * Обнуляет последнюю успешную последовательность
     */
    public static void resetLastSuccessSequence() {
        lastSuccessSequence = null;
    }
}