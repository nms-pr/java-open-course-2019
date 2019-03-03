package ru.mail.polis.open.task1;

import java.util.Scanner;

public class FizzBuzz implements IFizzBuzz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите первое число: ");
        int num_1 = in.nextInt();
        System.out.println("Введите второе число: ");
        int num_2 = in.nextInt();
        FizzBuzz f = new FizzBuzz();
        f.print(num_1, num_2);
        in.close();
    }
    @Override
    public void print(int from, int to) {
        for (int i = from; i <= to; i++)
        {
            if (i % 3 == 0 && i % 5 == 0)
            {
                System.out.println("FizzBizz");
            }

            else if (i % 5 == 0)
            {
                System.out.println("Buzz");
            }
            else if(i % 3 == 0)
            {
                System.out.println("Fizz");
            }
            else
            {
                System.out.println(i);
            }
        }
    }
}
