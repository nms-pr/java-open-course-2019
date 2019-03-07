package ru.mail.polis.open.task1.impl;

import ru.mail.polis.open.task1.interfaces.GameStrategy;
/**
 *
 * Implements the rules of FizzBuzz
 *
 * @author artyom.petrov
 * Since 07/03/2019
 */
public class FizzBuzzGameStrategy implements GameStrategy {

    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 100;

    @Override
    public String getCorrespondingString(int number) {

        if ((number < LOWER_BOUND) || (number > UPPER_BOUND)) {
            throw new IllegalArgumentException("Number must be in range from 0 to 100");
        }

        if ((number % 15) == 0) {
            return "FizzBuzz";
        }

        if ((number % 3) == 0) {
            return "Fizz";
        }

        if ((number % 5) == 0) {
            return "Buzz";
        }

        return "" + number;
    }
}
