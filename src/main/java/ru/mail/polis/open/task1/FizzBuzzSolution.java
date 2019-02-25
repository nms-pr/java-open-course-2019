package ru.mail.polis.open.task1;

public class FizzBuzzSolution implements FizzBuzz{

    //написал сам в лоб
    @Override
    public void print(int from, int to) {
        for (int counter = from; counter <= to; counter++){
            if (counter % 3 == 0 && counter % 5 == 0) System.out.println("FizzBuzz");
            else if (counter % 3 == 0) System.out.println("Fizz");
            else if (counter % 5 == 0) System.out.println("Buzz");
            else System.out.println(counter);
        }
    }

    //нашел на форумах, оказался быстрее
    @Override
    public void printByStrings(int from, int to) {
        final String EMPTY = "";
        for (int counter = from; counter <= to; counter++) {
            String value = EMPTY;
            if (counter % 3 == 0) value += "Fizz";
            if (counter % 5 == 0) value += "Buzz";
            if (value.equals(EMPTY)) value += counter;
            System.out.println(value);
        }
    }

    //тот же самый, только через StringBuilder => еще быстрее
    @Override
    public void printByStringBuilder(int from, int to) {
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
