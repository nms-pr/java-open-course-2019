package ru.mail.polis.open.task1;

public class Solution implements FizzBuzz {

    private static StringBuilder str = new StringBuilder();

    public static void main(String[] args) {
        Solution s = new Solution();
        s.print(1, 100);
    }


    @Override
    public void print(int from, int to) {

        for (int i = from; i <= to; i++) {
            str.setLength(0);

            if (i % 3 == 0) {

                str.append("Fizz");
            }
            if (i % 5 == 0) {
                str.append("Buzz");
            }
            if (str.length() == 0) {
                str.append(i);
            }
            System.out.println(str);
        }

    }
}

