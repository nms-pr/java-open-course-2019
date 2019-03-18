package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

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
    private static Stack<Character> stack;

    private final static char roOp = '(';
    private final static char cuOp = '{';
    private final static char sqOp = '[';
    private final static char roCl = ')';
    private final static char cuCl = '}';
    private final static char sqCl = ']';

    private static String _lastSuccessSequence = null;

    private static int _failChecksCount = 0;
    private static int _successChecksCount = 0;

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
        stack = new Stack<Character>();

        if (sequence.length() <= 100) {
            for(int i = 0; i < sequence.length(); i++) {
                switch (sequence.charAt(i)){
                    case (roOp):
                        stack.push(sequence.charAt(i));
                        break;
                    case (cuOp):
                        stack.push(sequence.charAt(i));
                        break;
                    case (sqOp):
                        stack.push(sequence.charAt(i));
                        break;
                    case (roCl):
                        if( !stack.empty() && stack.peek() == roOp) {
                            stack.pop();
                        } else {
                            _failChecksCount++;
                           return false;
                        }
                        break;
                    case (cuCl):
                        if(  !stack.empty() && stack.peek() == cuOp) {
                            stack.pop();
                        } else {
                            _failChecksCount++;
                            return false;
                        }
                        break;
                    case (sqCl):
                        if(  !stack.empty() && stack.peek() == sqOp) {
                            stack.pop();
                        } else {
                            _failChecksCount++;
                            return false;
                        }
                        break;
                    default:
                        _failChecksCount++;
                        return false;
                }
            }
            _successChecksCount++;
            _lastSuccessSequence = sequence;
            return true;
        }
        _failChecksCount++;
        return false;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        return _successChecksCount;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return _failChecksCount;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return _lastSuccessSequence;
    }
}
