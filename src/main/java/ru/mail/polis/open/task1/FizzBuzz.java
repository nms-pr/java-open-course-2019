package ru.mail.polis.open.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FizzBuzz implements IFizzBuzz {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num_1 = 0, num_2 = 0;
        try {
            System.out.println("Введите первое число: ");
            num_1 = Integer.parseInt(in.readLine());

            System.out.println("Введите второе число: ");
            num_2 = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Wrong number");
            e.printStackTrace();
        }


        FizzBuzz f = new FizzBuzz();
        f.print(num_1, num_2);
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
