package ru.mail.polis.open.task1;

import java.util.Scanner;

public class FizzBuzzTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите начальное число: ");
        int from = in.nextInt();
        System.out.print("Введите конечное число: ");
        int to = in.nextInt();
        FizzBuzzRealize fizzBuzz = new FizzBuzzRealize();
        if (from < to) {
            fizzBuzz.print(from, to);
        } else {
            System.out.println("Некорректный ввод");
        }
    }

}
