package ru.mail.polis.open.task1;

public class FizzBuzzGame implements FizzBuzz {

    /**
     * Напишите программу, которая выводит на экран числа от 1 до 100.
     * При этом вместо чисел, кратных трем,
     * программа должна выводить слово «Fizz»,
     * а вместо чисел, кратных пяти — слово «Buzz».
     * Если число кратно и 3, и 5,
     * то программа должна выводить слово «FizzBuzz».
     *
     * @param from - с какого числа начинать отсчёт
     * @param to   - каким числом заканчивать отсчёт
     */

    private static final int UPPER_BOUND = 100;
    private static final int LOWER_BOUND = 1;
    private static final int FIZZ_BUZZ_DIVIDER = 15;
    private static final int FIZZ_DIVIDER = 3;
    private static final int BUZZ_DIVIDER = 5;

    private boolean fromAndToAreCorrect(int from, int to) {
        if ((from >= LOWER_BOUND) && (to <= UPPER_BOUND)) {
            return true;
        }
        return false;
    }

    private String createWord(int i) {
        if (i % FIZZ_BUZZ_DIVIDER == 0) {
            return "FizzBuzz";
        }
        if (i % FIZZ_DIVIDER == 0) {
            return "Fizz";
        }
        if (i % BUZZ_DIVIDER == 0) {
            return "Buzz";
        }
        return String.valueOf(i);
    }

    @Override
    public void print(int from, int to) {
        if (fromAndToAreCorrect(from, to)) {
            String word;
            for (int i = from; i <= to; i++) {
                word = createWord(i);
                System.out.print(word + " ");
            }
            System.out.println();
        } else {
            throw new IllegalArgumentException("From should not be less than 1, To should not be more than 100");
        }

    }
}
