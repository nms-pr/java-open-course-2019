package ru.mail.polis.open.task3;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.Nullable;

/**
 * Для проверки класса на корректность следует использовать тесты. Команда
 * {@code ./gradlew clean build} должна завершаться корректно.
 * <p>
 * При решении задания следует обратить внимание на использование ключевых слов {@code final} и
 * {@code static}
 * <p>
 * В результирующим PR нужно предоставить - Код решения Реализовать все методы Весь код внутри
 * CorrectBracketSequencePredicate Сигнатуры класса, конструктора и метода следует оставить
 * неизменными Комментарии оставить неизменными Можно добавлять дополнительные методы - Тесты Внутри
 * package ru.mail.polis.open.task3 В нём будут видны public / protected / package_private методы
 */
public final class CorrectBracketSequenceChecker {

    private static Deque<Character> stack;

    private static int successCount;
    private static int failCount;
    private static String lastCorrectSequence;

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    {
        init();
    }

    static void init() {
        successCount = 0;
        failCount = 0;
        lastCorrectSequence = null;
    }

    /**
     * Метод проверяющий скобочную последовательность на правильность.
     * <p>
     * Пустая строка — правильная скобочная последовательность. Правильная скобочная
     * последовательность, взятая в скобки одного типа — правильная скобочная последовательность.
     * Правильная скобочная последовательность, к которой слева или справа приписана правильная
     * скобочная последовательность — правильная скобочная последовательность.
     * <p>
     * Последовательности из больше чем ста символов или с символами не скобок — некорректные.
     * <p>
     * Скобки бывают: 1. Круглые '(', ')' 2. Квадратные '[', ']' 3. Фигурные '{', '}'
     *
     * @param sequence — входная строка
     * @return {@code true} — если скобочная последовательность корректна и {@code false} иначе
     * @throws IllegalArgumentException если в строке содержатся символы, не являющиеся скобками или
     *         если входная строка содержит больше ста символов
     */
    public static boolean checkSequence(@Nullable String sequence) throws IllegalArgumentException {
        if (sequence.length() > 100) {
            failCount++;
            throw new IllegalArgumentException("Sequence length is more than 100");
        }

        stack = new ArrayDeque<Character>(sequence.length());


        for (int i = 0; i < sequence.length(); i++) {
            char current = sequence.charAt(i);

            if (isOpenBracket(current)) {
                stack.push(current);
            } else if (isCloseBracket(current)) {
                if (stack.isEmpty() || !isBracketsAnnihilates(current, stack.pop())) {
                    failCount++;
                    return false;
                }
            } else {
                failCount++;
                throw new IllegalArgumentException(current + " not a bracket");
            }
        }

        if (stack.isEmpty()) {
            lastCorrectSequence = sequence;
            successCount++;
            return true;
        } else {
            failCount++;
            return false;
        }
    }

    public static boolean isOpenBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    public static boolean isCloseBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    public static boolean isBracketsAnnihilates(char current, char previous) {
        return (current == ')' && previous == '(') || (current == ']' && previous == '[')
                || (current == '}' && previous == '{');
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось, что входная строка является
     * правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return successCount;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось, что входная строка не
     * является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return failCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastCorrectSequence;
    }
}
