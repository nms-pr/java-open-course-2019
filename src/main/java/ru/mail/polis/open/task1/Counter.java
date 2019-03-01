package ru.mail.polis.open.task1;

public class Counter implements FizzBuzz {
    public void print(int from,int to){
        String result;
        for (int i = from; i <= to; i++) {
            result = "";
            if (i % 3 == 0 || i % 5 == 0) {
                if (i % 3 == 0)
                    result += "Fizz";
                if (i % 5 == 0)
                    result += "Buzz";
            } else
                result += i;
            System.out.println(result);
        }
    }
}

