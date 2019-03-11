package ru.mail.polis.open.task1;

public class Solution implements FizzBuzz {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.print(1,100);
    }

    @Override
    public void print(int from, int to) {
        for (int i = from; i <= to; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    System.out.println("FizzBuzz");
                }
                else {
                    System.out.println("Fizz");
                }
            }
            else {
                if (i % 5 == 0) {
                    System.out.println("Buzz");
                }
                else {
                    System.out.println(i);
                }
            }
        }
    }
}
