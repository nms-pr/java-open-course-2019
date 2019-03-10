package ru.mail.polis.open.task1;

public class FizzBuzzGame implements FizzBuzz{

    @Override
    public void print(int from, int to) {

        if (from > to) {
            throw new IllegalArgumentException("\"From\" must be less than \"to\".");
        }

        if (from < 1 || to > 100){
            throw new IllegalArgumentException("Numbers must be in the range from 1 to 100.");
        }

        for (int i = from; i <= to; i++) {

            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println("" + i);
            }
        }
    }
}
