package ru.mail.polis.open.task1;

public class Main {

        public static void main(String[] args) {
            FizzBuzz obj = new FizzBuzzImpl();
            int from = 1;
            int to = 100;
            obj.print(from,to);
        }
}
