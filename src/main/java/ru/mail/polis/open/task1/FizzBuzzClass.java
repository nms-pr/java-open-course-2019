package ru.mail.polis.open.task1;

class FizzBuzzClass implements FizzBuzz {
    @Override
    public void print(int from, int to) {
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
