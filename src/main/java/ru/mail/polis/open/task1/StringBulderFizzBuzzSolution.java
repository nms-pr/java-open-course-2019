package ru.mail.polis.open.task1;

public class StringBulderFizzBuzzSolution implements FizzBuzz{

    //тот же самый, что и StringFizzBuzzSolution,
    //только через StringBuilder => победитель
    @Override
    public void print(int from, int to) {
        StringBuilder builder = new StringBuilder();
        for (int counter = from; counter <= to; counter++) {
            final int length = builder.length();
            if (counter % 3 == 0) builder.append("Fizz");
            if (counter % 5 == 0) builder.append("Buzz");
            if (length == builder.length()) builder.append(counter);
            builder.append('\n');
        }
        System.out.println(builder);
    }

}
