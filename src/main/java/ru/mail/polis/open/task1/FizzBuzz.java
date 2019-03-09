package ru.mail.polis.open.task1;

/**
 * @author bogdan.maschenko
 * Since 08/03/2019
 */
public interface FizzBuzz {


    void print(int from, int to) throws NumberMismatchException;
}

class Task1 implements FizzBuzz {

    @Override
    public void print(int from, int to) throws NumberMismatchException {
        if ((from != 1) || (to != 100)) {
            throw new NumberMismatchException("Number mismatching");
        }

        for (int i = from; i <= to; i++) {

            if (i % 15 == 0){
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

    public static void main(String[] args) {

        Task1 task1 = new Task1();
        try {
            task1.print(1, 100);
        } catch (NumberMismatchException e) {
            System.out.println("from > to, change it!");
            e.printStackTrace();
        }
    }
}

class NumberMismatchException extends Exception {
    NumberMismatchException(String msg) {
        super(msg);
    }
}
