package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
interface IFizzBuzz{

    void print ( int from, int to);

}

public class FizzBuzz implements IFizzBuzz {

    StringBuilder sb = new StringBuilder();



    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        fizzBuzz.print(1,100);
    }

    @Override
    public void print(int from, int to) {
        for (int i = from; i < to + 1; i++) {
            sb.setLength(0);
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.toString().length() == 0) {
                sb.append(i);
            }
            System.out.println(sb.toString());

        }
    }
}