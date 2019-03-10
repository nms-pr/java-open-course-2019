package ru.mail.polis.open.task1;

class FizzBuzzImpl implements FizzBuzz {

    public void print(int from, int to) {
        
        boolean check;
        for (int i = from; i <= to; i++) {

            check = false;
            if (i % 3 == 0) {
                System.out.print("Fizz");
                check = true;
            }
            if (i % 5 == 0) {
                System.out.print("Buzz");
                check = true;
            }
            if (!check) {
                System.out.println(i);
            } else {
                System.out.println();
            }
        }
    }
}


