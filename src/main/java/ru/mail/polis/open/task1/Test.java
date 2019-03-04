package ru.mail.polis.open.task1;
public class Test implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        for (int i = from; i < to + 1; i++) {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
            else if (i % 3 == 0) {
                System.out.println("Buzz");
            }
            else {
                System.out.println(i);
            }
        }

    }

    public static void main(String[] args) {
        new Test().print(1, 100);
    }
}