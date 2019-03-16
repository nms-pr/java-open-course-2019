package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private static final List<Character> openBrackets = new ArrayList<>(
            Arrays.asList('(', '[', '{')
    );

    private static final List<Character> closeBrackets = new ArrayList<>(
            Arrays.asList(')', ']', '}')
    );

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    static boolean isBracket(char c, boolean isOpen) {
        List<Character> brackets;

        if (isOpen) {
            brackets = openBrackets;
        } else {
            brackets = closeBrackets;
        }

        for (char bracket : brackets) {
            if (c == bracket) {
                return true;
            }
        }

        return false;
    }

    private static void onSuccessfullCheck(@Nullable String sequence) {
        Statistic.successChecksCount++;
        Statistic.lastSuccessSequence = sequence;
    }

    private static void onFailedCheck() {
        Statistic.failChecksCount++;
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
        if (sequence.isEmpty()) {
            onSuccessfullCheck(sequence);
            return true;
        }

        if (sequence.length() > 100) {
            onFailedCheck();
            throw new IllegalArgumentException();
        }

        Stack<Character> open = new Stack<>();

        for (char c : sequence.toCharArray()) {

            if (isBracket(c, true)) {
                open.push(c);
            } else if (isBracket(c, false)) {

                if (open.empty()) {
                    onFailedCheck();
                    return false;
                }

                char currentOpen = open.pop();

                int indexOpenBracket = openBrackets.indexOf(currentOpen);
                int indexCloseBracket = closeBrackets.indexOf(c);

                if (indexOpenBracket != indexCloseBracket) {
                    onFailedCheck();
                    return false;
                }

            } else {
                onFailedCheck();
                throw new IllegalArgumentException();
            }

        }

        if (!open.empty()) {
            onFailedCheck();
            return false;
        }

        onSuccessfullCheck(sequence);
        return true;

    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return Statistic.successChecksCount;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return Statistic.failChecksCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return Statistic.lastSuccessSequence;
    }

    static class Statistic {
        static int successChecksCount;
        static int failChecksCount;
        static String lastSuccessSequence;
    }
}
