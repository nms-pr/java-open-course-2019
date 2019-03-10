package ru.mail.polis.open.task1.solution;

import ru.mail.polis.open.task1.FizzBuzz;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
public abstract class AbstractFizzBuzzGame implements FizzBuzz {

    private static final int UPPER_BOUND = 100;
    private static final int LOWER_BOUND = 0;
    private static final int FIZZ_BUZZ_DIVIDER = 15;
    private static final int FIZZ_DIVIDER = 3;
    private static final int BUZZ_DIVIDER = 5;

    private final String wordDelimiter;

    public AbstractFizzBuzzGame() {
        this(" ");
    }

    public AbstractFizzBuzzGame(String wordDelimiter) {
        this.wordDelimiter = wordDelimiter;
    }

    static void checkArgs(int from, int to) {
        if (from < LOWER_BOUND) {
            throw new IllegalArgumentException("from should be in range [0..100]. from = " + from);
        }
        if (to > UPPER_BOUND) {
            throw new IllegalArgumentException("to should be in range [0..100]. to = " + to);
        }
        if (from > to) {
            throw new IllegalArgumentException("from should be less or equal to. from = " + from + ", to = " + to);
        }
    }

    static String generateWord(int number) {
        if (number < LOWER_BOUND || number > UPPER_BOUND) {
            throw new IllegalArgumentException("number should be in range [0..100]");
        }
        if (number % FIZZ_BUZZ_DIVIDER == 0) {
            return "FizzBuzz";
        }
        if (number % BUZZ_DIVIDER == 0) {
            return "Buzz";
        }
        if (number % FIZZ_DIVIDER == 0) {
            return "Fizz";
        }
        return Integer.toString(number);
    }


    @Override
    public void print(int from, int to) {
        checkArgs(from, to);
        for (int number = from; number < to; number++) {
            print(generateWord(number));
            print(wordDelimiter);
        }
        print(generateWord(to));
        flush();
    }

    abstract void print(String word);

    abstract void flush();

}
