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

    private static String lastCorrectSequence;
    private static int amountOfCorrectSequences;
    private static int amountOfWrongSequences;

    private static Deque<Character> stack = new ArrayDeque<>();

    private static final Character ROUND_OPEN_BRACKET = '(';
    private static final Character ROUND_CLOSE_BRACKET = ')';
    private static final Character SQUARE_OPEN_BRACKET = '[';
    private static final Character SQUARE_CLOSE_BRACKET = ']';
    private static final Character CURLY_OPEN_BRACKET = '{';
    private static final Character CURLY_CLOSE_BRACKET = '}';

    private CorrectBracketSequenceChecker() {
        amountOfCorrectSequences = 0;
        amountOfWrongSequences = 0;
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
        //бросаем исключение, если строка null или ее длина больше 100
        char[] sequenceArray;
        if (sequence == null || sequence.length() > 100) {
            throw new IllegalArgumentException();
        } else {
            sequenceArray = sequence.toCharArray();
        }
        stack.clear();
        Character previousBracket;
        //главный цикл
        for (char c : sequenceArray) {
            previousBracket = stack.peekFirst();
            Character currentBracket = c;
            try {
                // кладем, если стек пустой или скобки вложенные
                if (previousBracket == null || checkTwoInners(previousBracket, currentBracket)) {
                    pushInStack(currentBracket, stack);
                    // иначе проверяем на правильное закрытие скобок и достаем насовсем
                } else if (!checkTwoOpenClose(previousBracket, currentBracket)) {
                    throw new IllegalArgumentException();
                } else {
                    stack.pop();
                }
            } catch (IllegalArgumentException e) {
                amountOfWrongSequences++;
                return false;
            }
        }
        amountOfCorrectSequences++;
        lastCorrectSequence = sequence;
        return true;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return amountOfCorrectSequences;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return amountOfWrongSequences;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return lastCorrectSequence;
    }

    /**
     * Проверяет, что символ является открывающей скобкой и кладет в стек
     *
     * @throws IllegalArgumentException если не является открывающей скобкой
     */
    private static void pushInStack(Character character, Deque<Character> stack) {
        if (!character.equals(ROUND_OPEN_BRACKET)
            && !character.equals(SQUARE_OPEN_BRACKET)
            && !character.equals(CURLY_OPEN_BRACKET)) {
            throw new IllegalArgumentException();
        }
        stack.push(character);
    }

    /**
     * @param open  открывающая скобка
     * @param close закрывающая скобка
     * @return true если пара скобок корректна
     */
    static boolean checkTwoOpenClose(Character open, Character close) {
        switch (open) {
            case '(':
                return close.equals(ROUND_CLOSE_BRACKET);
            case '[':
                return close.equals(SQUARE_CLOSE_BRACKET);
            case '{':
                return close.equals(CURLY_CLOSE_BRACKET);
            default:
                return false;
        }
    }

    /**
     * @param first  первая скобка
     * @param second вторая скобка
     * @return true если обе скобки являются открывающими
     */
    static boolean checkTwoInners(Character first, Character second) {
        boolean isOpen = second.equals(ROUND_OPEN_BRACKET)
            || second.equals(SQUARE_OPEN_BRACKET)
            || second.equals(CURLY_OPEN_BRACKET);
        switch (first) {
            case '(':
                return isOpen;
            case '[':
                return isOpen;
            case '{':
                return isOpen;
            default:
                return false;
        }
    }

    /**
     * Обнуляет переменные статистики
     * Используется для более корректного тестирования
     */
    static void refreshStatistics() {
        amountOfWrongSequences = 0;
        amountOfCorrectSequences = 0;
        lastCorrectSequence = null;
    }
}