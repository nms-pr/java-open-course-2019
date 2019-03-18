package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

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

    private static final String ROUND_BRACKETS = "()";
    private static final String SQUARE_BRACKETS = "[]";
    private static final String FIGURATE_BRACKETS = "{}";

    private static int successChecks;
    private static int failChecks;
    private static String lastSuccessSequence;

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
        String tmpSequence = sequence;

        if (sequence == null) {
            return false;
        }

        if (sequence == "") {
            successChecks++;
            lastSuccessSequence = tmpSequence;
            return true;
        }

        if (sequence.length() > 100) {
            throw new IllegalArgumentException("входная строка содержит больше ста символов: " + sequence.length());
        }

        char[] brackets = (ROUND_BRACKETS + SQUARE_BRACKETS + FIGURATE_BRACKETS).toCharArray();
        boolean isBracket = false;
        for (int i = 0; i < sequence.length(); i++) {
            for (int j = 0; j < brackets.length; j++) {
                if (brackets[j] == sequence.charAt(i)) {
                    isBracket = true;
                    break;
                }
            }
            if (!isBracket) {
                throw new IllegalArgumentException("символ не скобка: " + sequence.charAt(i));
            }
            isBracket = false;
        }

        while (sequence.contains(ROUND_BRACKETS)
                || sequence.contains(SQUARE_BRACKETS)
                || sequence.contains(FIGURATE_BRACKETS)) {
            sequence = sequence.replace(ROUND_BRACKETS, "");
            sequence = sequence.replace(SQUARE_BRACKETS, "");
            sequence = sequence.replace(FIGURATE_BRACKETS, "");
        }

        if (sequence.length() != 0) {
            failChecks++;
            return false;
        }

        successChecks++;
        lastSuccessSequence = tmpSequence;
        return true;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return successChecks;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return failChecks;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
    }

    public static void setDefault() {
        successChecks = 0;
        failChecks = 0;
        lastSuccessSequence = null;
    }
}
