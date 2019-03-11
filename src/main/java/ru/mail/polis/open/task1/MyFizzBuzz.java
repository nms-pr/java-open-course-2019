package ru.mail.polis.open.task1;

public class MyFizzBuzz implements FizzBuzz  {
    @java.lang.Override
    public void print(int from, int to) {
        for (int index = 1; i <= 100; index) {
            switch (index % 15) {
                case 0:
                    System.out.println("FizzBuzz");
                    break;
                case 3:
                case 6:
                case 9:
                case 12:
                    System.out.println("Fizz");
                    break;
                case 5:
                case 10:
                    System.out.println("Buzz");
                    break;
                default:
                    System.out.println(index);
            }
        }
    }
}
