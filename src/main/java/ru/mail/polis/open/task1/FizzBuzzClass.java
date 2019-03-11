package ru.mail.polis.open.task1;

public class FizzBuzzClass implements FizzBuzz {
    private static final int UPPER_BOUND = 100;
    private static final int LOWER_BOUND = 0;
    private static final int FIZZ_DIVIDER = 3;
    private static final int BUZZ_DIVIDER = 5;
    private static String WORD_DELIMITER = "\n";


    public static void main(String[] args) {
        FizzBuzzClass fizzBuzz = new FizzBuzzClass();
        fizzBuzz.print(1, 100);
    }

    public static void setWordDelimiter(String wordDelimiter) {
        WORD_DELIMITER = wordDelimiter;
    }

    private static void checkArgs(int from, int to) {
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

    @Override
    public void print(int from, int to) {
        checkArgs(from, to);
        for (; from <= to; from++) {
            if (from % FIZZ_DIVIDER == 0) {
                System.out.print("Fizz");
            }
            if (from % BUZZ_DIVIDER == 0) {
                System.out.print("Buzz");
            }
            if ((from % BUZZ_DIVIDER != 0) & (from % FIZZ_DIVIDER != 0)) {
                System.out.print(from);
            }

            System.out.print(WORD_DELIMITER);

        }
    }
}