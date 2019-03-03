package ru.mail.polis.open.task1;

public interface Writer {
    static void print (int from, int to)
    {
        for (int i = from;i < to + 1;i++)
        {
            if (i % 3 == 0)
                if (i % 5 == 0)
                    System.out.println("FizzBuzz");
                else
                    System.out.println("Fizz");
            else
                if (i % 5 == 0)
                    System.out.println("Buzz");
                else
                    System.out.println(i);
        }
    }
    static void main(String[] args)
    {
        print(1,100);
    }
}
