package ru.mail.polis.open.task1.solution;

import ru.mail.polis.open.task1.FizzBuzz;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
public abstract class AbstractFizzBuzzGame implements FizzBuzz {

    protected final String WORD_DELIMITER;

    public AbstractFizzBuzzGame() {
        this(" ");
    }

    public AbstractFizzBuzzGame(String wordDelimiter) {
        WORD_DELIMITER = wordDelimiter;
    }

    /**
     * Напишите программу, которая выводит на экран числа от 1 до 100.
     * При этом вместо чисел, кратных трем,
     * программа должна выводить слово «Fizz»,
     * а вместо чисел, кратных пяти — слово «Buzz».
     * Если число кратно и 3, и 5,
     * то программа должна выводить слово «FizzBuzz»
     *
     * @param from - с какого числа начинать отсчёт
     * @param to   - каким числом заканчивать отсчёт
     */
    @Override
    public void print(int from, int to) {
        checkArgs(from, to);
        for (int number = from; number < to; number++) {
            print(generateWord(number));
            print(WORD_DELIMITER);
        }
        print(generateWord(to));
        flush();
    }

    abstract void print(String word);

    abstract void flush();

    void checkArgs(int from, int to) {
        if (from < 0) {
            throw new IllegalArgumentException("from should be in range [0..100]. from = " + from);
        }
        if (to > 100) {
            throw new IllegalArgumentException("to should be in range [0..100]. to = " + to);
        }
        if (from > to) {
            throw new IllegalArgumentException("from should be less or equal to. from = " + from + ", to = " + to);
        }
    }

    String generateWord(int number) {
        if (number < 0 || number > 100) {
            throw new IllegalArgumentException("number should be in range [0..100]");
        }
        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        return "" + number;
    }

}
