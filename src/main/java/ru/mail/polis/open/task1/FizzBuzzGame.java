package ru.mail.polis.open.task1;

public class FizzBuzzGame implements FizzBuzz {

    @Override
    public void print(int from, int to) {
        for(int i = from; i <= to; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                System.out.println("FizzBuzz");
                continue;
            }
            if (i % 3 == 0) {
                System.out.println("Fizz");
                continue;
            }
            if (i % 5 == 0) {
                System.out.println("Buzz");
                continue;
            }
            System.out.println(i);
        }
    }
}
