package ru.mail.polis.open.task1;

import java.io.PrintStream;

public class SimpleFizzBuzz implements FizzBuzz {
    private static final char DEFAULT_SEPARATOR = '\n';

    @Override
  public void print(int from, int to) throws IllegalArgumentException {
        this.print(from, to, System.out, DEFAULT_SEPARATOR);
    }

    public void print(int from, int to, PrintStream out) throws IllegalArgumentException {
        this.print(from, to, out, DEFAULT_SEPARATOR);
    }

    /**
   * Выводит числа от 1 до 100 в PrintStream. При этом вместо чисел, кратных трем, выводит слово
   * «Fizz», а вместо чисел, кратных пяти — слово «Buzz». Если число кратно и 3, и 5, то выводит
   * слово «FizzBuzz».
   *
   * @param from - с какого числа начинать отсчёт
   * @param to - каким числом заканчивать отсчёт
   * @param out - поток вывода
   * @param separator - разделитель между элементами
   */
    public void print(int from, int to, PrintStream out, char separator)
        throws IllegalArgumentException {
        if (from < 1 || to > 100) {
            throw new IllegalArgumentException("Range is not in [1..100]");
        }
        if (from > to) {
            throw new IllegalArgumentException("from is more than to");
        }

        for (int i = from; i <= to; i++) {
            if (i % 15 == 0) {
                out.print("FizzBuzz");
            } else if (i % 3 == 0) {
                out.print("Fizz");
            } else if (i % 5 == 0) {
                out.print("Buzz");
            } else {
                out.print(i);
            }
            out.print(separator);
        }
        out.close();
    }
}
