package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

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

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static char symbol;
    private static int uncorrectCounter = 0;
    private static int correctCounter = 0;
    private static String lastCorrectSequence;
    private static ArrayDeque<Character> deque = new ArrayDeque<>();

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
            correctCounter++;
            throw new IllegalArgumentException("Вы ввели пустую строку");
        }
        if (sequence.length() > 100) {
            uncorrectCounter++;
            throw new IllegalArgumentException("Длина строки больше 100 символов");
        }
        if (sequence.equals("")) {
            lastCorrectSequence = sequence;
            correctCounter++;
            return true;
        }

        for (int i = 0; i < sequence.length(); i++) {
            symbol = sequence.charAt(i);
            if ((symbol != '(')
                    && (symbol != ')')
                    && (symbol != '[')
                    && (symbol != ']')
                    && (symbol != '{')
                    && (symbol != '}')) {
                uncorrectCounter++;
                throw new IllegalArgumentException("Неверный символ в строке " + symbol);
            }
            if ((symbol == '(')
                    || (symbol == '[')
                    || (symbol == '{')) {
                deque.push(symbol);
                continue;
            } else if (deque.isEmpty()) {
                uncorrectCounter++;
                return false;
            }
            char currentBracket = symbol;
            char previousBracket = deque.peek();
            if ((currentBracket == ')' && previousBracket == '(') ||
                    (currentBracket == ']' && previousBracket == '[') ||
                    (currentBracket == '}' && previousBracket == '{')) {
                deque.pop();
            } else {
                uncorrectCounter++;
                return false;
            }
        }
        if (!deque.isEmpty()) {
            uncorrectCounter++;
            return false;
        } else {
            lastCorrectSequence = sequence;
            correctCounter++;
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
        return correctCounter;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return uncorrectCounter;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return correctCounter > 0 ? lastCorrectSequence : null;
    }

    public static void reset() {
        deque = new ArrayDeque<>();
        correctCounter = 0;
        uncorrectCounter = 0;
        lastCorrectSequence = null;
    }
}
