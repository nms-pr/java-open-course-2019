package ru.mail.polis.open.task1;

public class WorkingFB implements FizzBuzz {
    @Override
    public void print(int from, int to) {
        for (int i = from;i <= to;i++) {
            String s = "";
            if (i % 3 == 0) {
                s = "Fizz";
            }
            if (i % 5 == 0) {
                s += "Bazz";
            }
            if (s.equals("")) {
                s = s.valueOf(i);
            }
            System.out.println(s);
        }
    }

    public void print() {
        for (int i = 1;i <= 100;i++) {
            String s = "";
            if (i % 3 == 0) {
                s = "Fizz";
            }
            if (i % 5 == 0) {
                s += "Bazz";
            }
            if (s.equals("")) {
                s = s.valueOf(i);
            }
            System.out.println(s);
        }
    }
}
