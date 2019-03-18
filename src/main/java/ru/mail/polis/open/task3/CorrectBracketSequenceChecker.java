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



    private CorrectBracketSequenceChecker() {
        Statist.LastBrackString = null;
        /* todo: append code if needed */
    }

    final static char[] BrackOpen = new char[] {'(','[','{'};
    final static char[] BrackClose = new char[] {')',']','}'};
    final static Stats Statist = new Stats();

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
    public static  boolean Compare(char[] Brack, char Symbol){
        for(int i = 0; i < Brack.length; i++){
            if(Symbol == Brack[i]){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckOposit(char a, char b){
        int indexA = 0;
        int indexB = 0;
        for(int i = 0; i < BrackOpen.length; i++){
            if(a == BrackOpen[i]){
                indexA = i;
            }
            if(b == BrackClose[i]){
                indexB = i;
            }
        }
        if(indexA == indexB){
            return true;
        }
        else{
            return false;
        }
    }

    public static class Stats{
        int SuccessfulChecks;
        int FailChecks;
        String LastBrackString;
    }

    public static boolean checkSequence(@Nullable String sequence) {
        Stack<Character> BrackString = new Stack<>();
        if(sequence.length()>100){
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < sequence.length(); i++){
            if(Compare(BrackOpen, sequence.charAt(i))){
                BrackString.push(sequence.charAt(i));
            }
            else{
                if(Compare(BrackClose, sequence.charAt(i))){
                    if(CheckOposit(BrackString.peek(), sequence.charAt(i))){
                        BrackString.pop();
                    }
                    else{//скобка не закрылась
                        Statist.FailChecks++;
                        return false;
                    }
                }
                else{// встеретилось что-то кроме скобок
                    throw new IllegalArgumentException();
                }
            }
        }
        if(BrackString.size()==0){
            Statist.SuccessfulChecks++;
            Statist.LastBrackString = sequence;
            return true;
        }
        else{
            Statist.FailChecks++;
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
        return Statist.SuccessfulChecks;
    }

    /**
     * Возвращает количество проверок, в результате которых выяснилось,
     * что входная строка не является правильной скобочной последовательностью.
     *
     * @return количество неудачных проверок
     */
    public static int getFailChecksCount() {
        return Statist.FailChecks;
    }

    /**
     * Возвращает последнюю последовательность, проверка которой завершилась успешно.
     *
     * @return последняя правильная скобочная последовательность или null если такой ещё не было
     */
    public static @Nullable String getLastSuccessSequence() {
        return Statist.LastBrackString;
    }
}
