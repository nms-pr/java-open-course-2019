package ru.mail.polis.open.task1;

public class Task1 implements FizzBuzz {

    @Override
    public void print(int from, int to) {
        for (int j = from; j <= to; j++) {
            if (j % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (j % 3 == 0) {
                System.out.println("Fizz");
            } else if (j % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(j);
            }
        }
    }

    public static void main(String[] args) {
        Task1 task = new Task1();
        task.print(1, 100);
    }
}
