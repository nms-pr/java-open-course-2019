package ru.mail.polis.open.task1;

public class Main implements FizzBuzz {

    public static void main(String[] args) {
        int from = 1;
        int to = 100;
        Main m = new Main();
        m.print(from, to);
    }

    @Override
    public void print(int from, int to) {
        if (from < 1) {
            throw new IllegalArgumentException("From must be more then 0");
        }
        if (to > 100) {
            throw new IllegalArgumentException("To must be less then 100");
        }
        if (from > to) {
            throw new IllegalArgumentException("From must be less then to");
        }
        for (; from < to + 1; from++) {
            if (from % 15 == 0) {
                System.out.println("FizzBuzz");
                continue;
            }
            if (from % 3 == 0) {
                System.out.println("Fizz");
                continue;
            }
            if (from % 5 == 0) {
                System.out.println("Buzz");
                continue;
            }
            System.out.println(from);
        }
    }
}
