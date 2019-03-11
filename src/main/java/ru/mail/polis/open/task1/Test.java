package ru.mail.polis.open.task1;

class Test implements FizzBuzz{
    public void print(int from, int to) {
        if (from > to) {
            System.out.println("\"from\" must be smaller than \"to\".");
        } else {
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
    }

    public static void main(String[] args) {
        FizzBuzz fizzbuzz = new Test();
        fizzbuzz.print(1, 100);
    }
}