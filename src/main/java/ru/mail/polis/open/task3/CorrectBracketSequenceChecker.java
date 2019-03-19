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

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }
    static final char OPEN_ROUND_BRACE = '(';
    static final char CLOSE_ROUND_BRACE = ')';
    static final char OPEN_SQUARE_BRACE = '[';
    static final char CLOSE_SQUARE_BRACE = ']';
    static final char OPEN_FIGURE_BRACE = '{';
    static final char CLOSE_FIGURE_BRACE = '}';
    static int numberOfSuccessfulChecks = 0;
    static int numberOfUnsuccessfulChecks = 0;
    static String lastSuccessSequence = "";

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
        int bracesCounter = 0;

        if (sequence.length() == 0) {
            return true;
        }
        if (sequence.length() > 100) {
            return false;
        }
        char[] parsedString = sequence.toCharArray();

        for (int i = 0; i < parsedString.length; i++) {

            if ((parsedString[i] == OPEN_ROUND_BRACE) | (parsedString[i] == OPEN_FIGURE_BRACE)
                 | (parsedString[i] == OPEN_SQUARE_BRACE)) {
                bracesCounter++;
            } else if ((parsedString[i] == CLOSE_ROUND_BRACE) | (parsedString[i] == CLOSE_FIGURE_BRACE)
                         | (parsedString[i] == CLOSE_SQUARE_BRACE)) {
                bracesCounter--;
            } else {
                throw new IllegalArgumentException("Unacceptable symbols");
            }

        }
        if (bracesCounter == 0) {
            lastSuccessSequence = sequence;
            numberOfSuccessfulChecks++;
            return true;
        } else {
            numberOfUnsuccessfulChecks++;
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
        return numberOfSuccessfulChecks;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return numberOfUnsuccessfulChecks;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        if (lastSuccessSequence.equals("")) {
            return null;
        } else {
            return lastSuccessSequence;
        }
    }
}
