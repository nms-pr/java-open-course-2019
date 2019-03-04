package ru.mail.polis.open.task1;

public class run implements FizzBuzz{
    public static void main(String[] args) {
        run ob = new run();
        ob.print(0, 100);
    }
    public void print(int from, int to) {
        for (int i = from; i <= to; i++) {
            if ((i % 3 == 0) & (i % 5 == 0)) System.out.println("FizzBuzz");
            else if (i % 5 == 0) System.out.println("Buzz");
            else if (i % 3 == 0) System.out.println("Fizz");
            else System.out.println(i);
        }
    }
}
