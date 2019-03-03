package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
interface FizzBuzz {
    public class BuzzFizz implements FizzBuzz {
        public void doIt() {
            int i;
            String s = "";
            for (i = 1; i < 101; ++i) {
                if (i % 3 == 0)
                    s += "Fizz";
                else if (i % 5 == 0)
                    s += "Buzz";
                else if (i % 3 == 0 && i % 5 == 0)
                    s += "FizzBuzz";
                else s += Integer.toString(i);
                System.out.print(s + "\r");

            }
        }

        public static void main(String[] args) {
            BuzzFizz buzzFizz = new BuzzFizz();
            buzzFizz.doIt();
        }

    }
}