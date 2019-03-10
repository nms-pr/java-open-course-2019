package ru.mail.polis.open.task1;

/**
 * @author nikita.kulikov
 * Since 01/03/2019
 */

public interface FizzBuzz {

    /**
     * Напишите программу, которая выводит на экран числа от 1 до 100.
     * При этом вместо чисел, кратных трем,
     * программа должна выводить слово «Fizz»,
     * а вместо чисел, кратных пяти — слово «Buzz».
     * Если число кратно и 3, и 5,
     * то программа должна выводить слово «FizzBuzz».
     *
     * @param from - с какого числа начинать отсчёт
     * @param to   - каким числом заканчивать отсчёт
     */
    void print(int from, int to);
}

class Change implements FizzBuzz {
    public void print(int from, int to) {
        for (int i = from; i <= to; i++) {
            if ((i % 3 == 0) && (i % 5 == 0))
                System.out.println("FizzBuzz");
            else
            if (i % 3 == 0)
                System.out.println("Fizz");
            else
            if (i % 5 == 0)
                System.out.println("Buzz");
            else
                System.out.println(i);
        }
    }
}

