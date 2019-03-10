package ru.mail.polis.open.task1;

class FizzBuzzClass implements FizzBuzz {

    private static final int UPPER_BOUND = 100;
    private static final int LOWER_BOUND = 0;
    private static final int FIZZ_DIVIDER = 3;
    private static final int BUZZ_DIVIDER = 5;

    @Override
    public void print(int from, int to) {
        if (from < LOWER_BOUND || to > UPPER_BOUND || from > to)
            throw new IllegalArgumentException();
        StringBuilder out = new StringBuilder();
        for (int i = from; i <= to; i++) {
            if (i % 3 == 0)
                out.append("Fizz");
            if (i % 5 == 0)
                out.append("Buzz");
            if (out.length() == 0) System.out.println(i);
            else System.out.println(out);
            out.delete(0, out.length());
        }
    }
}
