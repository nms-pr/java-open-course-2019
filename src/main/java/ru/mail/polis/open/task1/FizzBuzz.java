package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
public interface FizzBuzz {

    /**
     * Напишите программу, которая выводит на экран числа от 1 до 100.
     * При этом вместо чисел, кратных трем,
     * программа должна выводить слово «Fizz»,
     * а вместо чисел, кратных пяти — слово «Buzz».
     * Если число кратно и 3, и 5,
     * то программа должна выводить слово «FizzBuzz»
     *
     * @param from - с какого числа начинать отсчёт
     * @param to   - каким числом заканчивать отсчёт
     */
    void print(int from, int to);
}

public class Main implements FizzBuzz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str;
        int from = in.nextInt();
        int to = in.nextInt();
        print(from, to);
    }

    static void print(int from, int to) {
        while (from < to + 1) {
            str = "";
            if (from % 3 == 0) {
                str = "Fizz";
            }
            if (from % 5 == 0) {
                str += "Buzz";
            }
            if (str == "") {
                System.out.println(from);
            } else {
                System.out.println(str);
            }
            from++;
        }
    }
}

