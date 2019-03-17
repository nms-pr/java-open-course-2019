package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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

    private static final int SYMBOL_AMOUNT_UPPER_BOUND = 100;
    private static final Map BRACKETS_MATCH;

    static {
        Map<Character, Character> localMap = new HashMap<>();
        localMap.put(')', '(');
        localMap.put(']', '[');
        localMap.put('}', '{');
        BRACKETS_MATCH = Collections.unmodifiableMap(localMap);
    }

    private static int failChecksCount = 0;
    private static int successChecksCount = 0;
    private static String lastSuccessSequence;
    private static Stack<Character> stack = new Stack<>();

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static void checkInput(@Nullable String input) {
        if (input == null || !input.matches("[()\\[\\]{}]*") || input.length() > SYMBOL_AMOUNT_UPPER_BOUND) {
            throw new IllegalArgumentException("Illegal input string");
        }
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
        checkInput(sequence);
        if (!sequence.isEmpty()) {
            stack.push(sequence.charAt(0));
            for (int i = 1; i < sequence.length(); i++) {
                char currentChar = sequence.charAt(i);
                if (BRACKETS_MATCH.containsKey(currentChar)
                        && !stack.isEmpty()
                        && BRACKETS_MATCH.get(currentChar).equals(stack.peek())) {
                    stack.pop();
                } else {
                    stack.push(currentChar);
                }
            }
        }
        if (stack.isEmpty()) {
            successChecksCount++;
            lastSuccessSequence = sequence;
            return true;
        } else {
            stack.clear();
            failChecksCount++;
            return false;
        }
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
    public static String getLastSuccessSequence() {
        if (successChecksCount == 0) {
            throw new IllegalStateException("There are no successful checks yet!");
        }
        return lastSuccessSequence;
    }

    public static void clear() {
        failChecksCount = 0;
        successChecksCount = 0;
        lastSuccessSequence = null;
    }
}
