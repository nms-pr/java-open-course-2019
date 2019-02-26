package ru.mail.polis.open.task1;

public class Program implements FizzBuzz {

    @Override
    public void print(int from, int to) {
        for (int counter = from; counter <= to; counter++){
            boolean mustNotPrintNumber = false;
            if (counter % 3 == 0) {
                System.out.print("Fizz");
                mustNotPrintNumber = true;
            }
            if (counter % 5 == 0) {
                System.out.print("Buzz");
                mustNotPrintNumber = true;
            }
            if (mustNotPrintNumber)
                System.out.println(counter);
        }
    }

}
