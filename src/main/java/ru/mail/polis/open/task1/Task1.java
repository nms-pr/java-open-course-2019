package ru.mail.polis.open.task1;

/**
 * @author rinefica
 */
public class Task1 implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        boolean isMultiple;

        for (int i = from; i <= to; i++) {
            isMultiple = false;

            if (i % 3 == 0) {
                System.out.print("Fizz");
                isMultiple = true;
            }

            if (i % 5 == 0) {
                System.out.print("Buzz");
                isMultiple = true;
            }

            if (!isMultiple) {
                System.out.println(i);
            } else {
                System.out.print("\n");
            }

        }
    }

    public static void main(String[] args) {
        new Task1().print(1, 100);
    }
}
