package ru.mail.polis.open.task1;

import java.util.Scanner;

public class WorkFizzBuzz {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начальное значение: ");
        int from = scanner.nextInt();
        System.out.println("Введите конечное значение: ");
        int to = scanner.nextInt();

        WorkingFB fizzbazz = new WorkingFB();
        if (from <= to) {
            fizzbazz.print(from, to);
        } else {
            System.out.println("Ошибка ввода!");
        }
        System.out.println();
        System.out.println("Вывод без параметров: от 1 до 100");
        fizzbazz.print();
    }
}
