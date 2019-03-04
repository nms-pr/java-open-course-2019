package ru.mail.polis.open.task1;

public class CFizzBuzz implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        int i;
        String s;

        for(i = from; i <= to; i++) {
            s = "";

            if(i%3 == 0) s += "Fizz";

            if(i%5 == 0) s += "Buzz";

            if(s.equals("")) s += i;

            System.out.println(s);
        }
    }
}
