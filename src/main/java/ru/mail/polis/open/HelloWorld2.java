package ru.mail.polis.open;

import ru.mail.polis.open.task1.FizzBuzz;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public class HelloWorld2 implements FizzBuzz {

    public static void main(String[] args) {
        System.out.println("Hello world2!");
        HelloWorld2 ob = new HelloWorld2();
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
