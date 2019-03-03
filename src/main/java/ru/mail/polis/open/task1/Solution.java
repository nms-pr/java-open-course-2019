package ru.mail.polis.open.task1;

public class Solution implements FizzBuzz {


    public static void main(String[] args) {
        Solution s = new Solution();
        s.print(1, 100);
    }


    @Override
    public void print(int from, int to) {
        for (int i = from; i < to; i++) {
            String str = "";


            if (i % 3 == 0) {

                str += "Fizz";
            }
            if (i % 5 == 0) {
                str += "Buzz";
            }
            if (str.length()==0)
            {
                str += i;
            }
            System.out.println(str);
        }

    }
}

