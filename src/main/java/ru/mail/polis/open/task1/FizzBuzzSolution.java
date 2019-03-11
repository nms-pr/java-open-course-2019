package ru.mail.polis.open.task1;

public class FizzBuzzSolution implements FizzBuzz {

    //написал сам в лоб
    @Override
    public void print(int from, int to) {
        for (int counter = from; counter <= to; counter++) {
            if (counter % 3 == 0 && counter % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (counter % 3 == 0) {
                System.out.println("Fizz");
            } else if (counter % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(counter);
            }
        }
    }
}
