package ru.mail.polis.open.task1;

public class FizzBuzzRealization implements FizzBuzz {
    public String delimeter = "\n";

    @Override
    public void print(int from, int to) {
        if (to > from) {
            for (int i = from; i <= to; i++) {
                if ((i % 3 == 0) || (i % 5 == 0)) {
                    if (i % 3 == 0) {
                        System.out.print("Fizz");
                    }
                    if (i % 5 == 0) {
                        System.out.print("Buzz");
                    }

                } else {
                    System.out.print(i);
                }
                System.out.println(delimeter);
            }
        }
    }

    public static void main(String[] args) {
        new FizzBuzzRealization().print(1,100);
    }
}
