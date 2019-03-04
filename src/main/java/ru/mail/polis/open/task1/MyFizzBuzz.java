package ru.mail.polis.open.task1;

public class MyFizzBuzz implements FizzBuzz
{
    public void print(int from, int to)
    {
        int del1 = 3;
        int del2 = 5;
        for(int i = from; i <= to; i++){
            if(i % del1 == 0) {
                System.out.print("Fizz");
            }
            if(i % del2 == 0){
                System.out.print("Buzz");
            }
            if(i % del2 == 0 || i % del1 == 0){
                System.out.println();
            }
            else{
                System.out.println(i);
            }
        }
    }
}
