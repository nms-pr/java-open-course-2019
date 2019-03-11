package ru.mail.polis.open.task1;

public class StringFizzBuzzSolution implements FizzBuzz {

    //нашел на форумах, оказался быстрее
    //(upd. не всегда, часто проигрывает на тех же самых числах)
    @Override
    public void print(int from, int to) {
        final String empty = "";
        for (int counter = from; counter <= to; counter++) {
            String value = empty;
            if (counter % 3 == 0) {
                value += "Fizz";
            }
            if (counter % 5 == 0) {
                value += "Buzz";
            }
            if (value.equals(empty)) {
                value += counter;
            }
            System.out.println(value);
        }
    }
}
