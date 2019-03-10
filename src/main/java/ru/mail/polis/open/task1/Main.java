package ru.mail.polis.open.task1;

public class Main {
    private static final int from = 1;
    private static final int to = 100;
    
    public static void main(String[] args) {
        FizzBuzz myClassTest = new MyClass();
        myClassTest.print(from, to);
    }
}
