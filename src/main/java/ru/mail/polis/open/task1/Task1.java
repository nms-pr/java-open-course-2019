package ru.mail.polis.open.task1;

public class Task1 implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        if ((from != 1) || (to != 100)) {
            try {
                throw new NumberMismatchException("Number mismatching");
            } catch (NumberMismatchException e) {
                e.printStackTrace();
            }
        }

        for (int i = from; i <= to; i++) {

            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {

        Task1 task1 = new Task1();
        task1.print(1, 100);
    }
}