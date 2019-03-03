package ru.mail.polis.open.task1;

public class FizzBuzzClass implements FizzBuzz {
    public static void main(String[] args) {
        FizzBuzzClass fb = new FizzBuzzClass();
        fb.print(1, 100);
    }

    public void print(int from, int to) {
        for(; from <= to; from++) {
            if (from % 3 == 0){
                System.out.print("Fizz");
            }
            if (from % 5 == 0){
                System.out.print("Buzz");
            }
            if ( (from % 5 != 0) & (from % 3 != 0) ){
                System.out.print(from);
            }

            System.out.print("\n");

        }
    }
}