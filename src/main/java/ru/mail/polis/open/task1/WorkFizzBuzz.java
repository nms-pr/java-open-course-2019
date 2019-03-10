package ru.mail.polis.open.task1;

import java.util.Scanner;

public class WorkFizzBuzz {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите начальное значение: ");
        int from = scanner.nextInt();
        System.out.println("Введите конечное значение: ");
        int to = scanner.nextInt();

        Working fizzbazz = new Working();
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

class Working implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        for(int i = from;i <= to;i++){
            String s ="";
            if (i%3==0) s = "Fizz";
            if (i%5==0) s+= "Bazz";
            if (s =="") s = s.valueOf(i);
            System.out.println(s);
        }
    }

    public void print() {
        for(int i = 1;i <= 100;i++){
            String s ="";
            if (i%3==0) s = "Fizz";
            if (i%5==0) s+= "Bazz";
            if (s =="") s = s.valueOf(i);
            System.out.println(s);
        }
    }
}

