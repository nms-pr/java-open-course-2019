package ru.mail.polis.open.task1;

public class FizzBuzzImpl implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = from; i <= to; i++) {
            if (i % 3 == 0) {
                stringBuilder.append("Fizz");
            }
            if (i % 5 == 0) {
                stringBuilder.append("Buzz");
            }
            if (stringBuilder.length() == 0) {
                stringBuilder.append(i);
            }
            System.out.println(stringBuilder);
            stringBuilder.setLength(0);
        }
    }

    public static void main(String[] args) {
        FizzBuzzImpl fizzBuzz = new FizzBuzzImpl();
        fizzBuzz.print(1, 100);
    }
}
