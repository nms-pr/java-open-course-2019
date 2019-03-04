package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
interface FizzBuzz {
    class BuzzFizz implements FizzBuzz {
        void print(int to, int from) {
            int i;
            String s = "";
            for (i = to; i < from; ++i) {
                if (i % 3 == 0)
                    s += "Fizz ";
                else if (i % 5 == 0)
                    s += "Buzz ";
                else if (i % 3 == 0 && i % 5 == 0)
                    s += "FizzBuzz ";
                else s = s + i + " ";
                System.out.print(s + "\r");

            }
        }

        public static void main(String[] args) {
            BuzzFizz buzzFizz = new BuzzFizz();
            buzzFizz.print(1,101);
        }

    }
}