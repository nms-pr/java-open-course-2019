package ru.mail.polis.open.task1;

public class FizzBuzzRealize implements FizzBuzz {

    @Override
    public void print(int from, int to) {
        for (int counter = from; counter <= to; counter++){
            boolean mustPrintNumber = true;
            if (counter % 3 == 0) {
                System.out.print("Fizz");
                mustPrintNumber = false;
            }
            if (counter % 5 == 0) {
                System.out.print("Buzz");
                mustPrintNumber = false;
            }
            if (mustPrintNumber)
                System.out.print(counter);
            System.out.println();
        }
    }

}
