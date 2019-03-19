package ru.mail.polis.open.task3;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static int count = 0;
    static Stack<Character> brackets = new Stack<>();

    private CorrectBracketSequenceChecker() {

        /* todo: append code if needed */
    }



    public static void main(String[] args) throws IOException {
        String result = sequenceReader();
        System.out.println(checkSequence(result));
    }

    private static String sequenceReader() throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String res = inputReader.readLine();
        res = res.trim();

        return res;
    }


    public static boolean checkSequence(@Nullable String sequence) throws IllegalArgumentException {
        if (sequence == null || sequence.length() > 100) {
            throw new IllegalArgumentException("Incorrect input string");
        }
        char[] bracketSequence = sequence.toCharArray();

        for(char item : bracketSequence){
            switch (item){
                case '(':
                    count ++;
                    brackets.push(item);
                    break;
                case '{':
                    count ++;
                    brackets.push(item);
                    break;
                case '[':
                    count ++;
                    brackets.push(item);
                    break;
                case ')':
                    if(count > 0 && brackets.pop() == '('){
                        count --;
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if(count > 0 && brackets.pop() == '{'){
                        count --;
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if(count > 0 && brackets.pop() == '['){
                        count --;
                    } else {
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return count == 0;
    }






    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка является правильной скобочной последовательностью.
     *
     * @return количество удачных проверок
     */
    public static int getSuccessChecksCount() {
        throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        throw new UnsupportedOperationException("todo: implement this");
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        throw new UnsupportedOperationException("todo: implement this");
    }
}
