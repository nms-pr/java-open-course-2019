package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
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

    private CorrectBracketSequenceChecker() {
        /* todo: append code if needed */
    }

    private static final char OPEN_ROUND_BRACE      = '(';
    private static final char OPEN_SQUARE_BRACE     = '[';
    private static final char OPEN_FIGURE_BRACE     = '{';
    private static final char CLOSE_ROUND_BRACE     = ')';
    private static final char CLOSE_SQUARE_BRACE    = ']';
    private static final char CLOSE_FIGURE_BRACE    = '}';

    private static final Character[] OPEN_BRACES     = { OPEN_ROUND_BRACE, OPEN_SQUARE_BRACE, OPEN_FIGURE_BRACE };
    private static final Character[] CLOSE_BRACES    = { CLOSE_ROUND_BRACE, CLOSE_SQUARE_BRACE, CLOSE_FIGURE_BRACE };

    private static int counterOfCorrectTries    = 0;
    private static int counterOfWrongTries      = 0;
    private static String lastSuccessSequence;

    private static Deque<Character> stack = new ArrayDeque<>();
    private static Character prevElementInStack;


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
        //пустая строка - правильная
        if (sequence.equals("")) {
            lastSuccessSequence = sequence;
            counterOfCorrectTries++;
            return true;
        }

        //длина строки больше ста символов
        if (sequence.length() > 100) {
            counterOfWrongTries++;
            throw new IllegalArgumentException("Length of the string should be less than 100");
        }

        for (int i = 0; i < sequence.length(); i++) {
            //Проверка на наличие символов, неявляющихся скобками
            if ((!Arrays.asList(OPEN_BRACES).contains(sequence.charAt(i)))
                    && (!Arrays.asList(CLOSE_BRACES).contains(sequence.charAt(i)))) {
                counterOfWrongTries++;
                stack.clear();
                throw new IllegalArgumentException("This symbol isn't bracket: " + sequence.charAt(i));
            }

            //Открытые скобки
            if (Arrays.asList(OPEN_BRACES).contains(sequence.charAt(i))) {
                stack.push(sequence.charAt(i));
            }

            //Проверка закрытых скобок. Последовательность: круглая, квадратная, фигурная
            for (int currentBrace = 0; currentBrace < 3; currentBrace++) {
                if (sequence.charAt(i) == CLOSE_BRACES[currentBrace]) {
                    if (stack.size() == 0) {
                        counterOfWrongTries++;
                        return false;
                    } else {
                        prevElementInStack = stack.pop();
                        if (prevElementInStack != OPEN_BRACES[currentBrace]) {
                            counterOfWrongTries++;
                            stack.clear();
                            return false;
                        }
                    }
                }
            }
        }

        if (stack.size() != 0) {
            counterOfWrongTries++;
            stack.clear();
            return false;
        }

        lastSuccessSequence = sequence;
        counterOfCorrectTries++;
        return true;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return counterOfCorrectTries;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return counterOfWrongTries;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastSuccessSequence;
    }

    public static void reset() {
        counterOfCorrectTries = 0;
        counterOfWrongTries = 0;
        lastSuccessSequence = null;
    }
}