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

    private static final Character roundOpenBracket = '(';
    private static final Character roundCloseBracket = ')';
    private static final Character squareOpenBracket = '[';
    private static final Character squareCloseBracket = ']';
    private static final Character curlyOpenBracket = '{';
    private static final Character curlyCloseBracket = '}';

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
        char[] sequenceArray;
        //return true if the sequence is empty
        if (sequence != null) {
            sequenceArray = sequence.toCharArray();
        } else {
            return true;
        }
        //проверяем длину
        if (sequenceArray.length > 100) {
            throw new IllegalArgumentException();
        }
        Deque<Character> stack = new ArrayDeque<>();
        Character previousBracket;

        //главный цикл
        for (int i = 0; i < sequenceArray.length; i++) {
            previousBracket = stack.peekFirst();
            Character currentBracket = sequenceArray[i];
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
        if (!character.equals(roundOpenBracket)
            && !character.equals(squareOpenBracket)
            && !character.equals(curlyOpenBracket)) {
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
                return close.equals(roundCloseBracket);
            case '[':
                return close.equals(squareCloseBracket);
            case '{':
                return close.equals(curlyCloseBracket);
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
        boolean isOpen = second.equals(roundOpenBracket)
            || second.equals(squareOpenBracket)
            || second.equals(curlyOpenBracket);
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
