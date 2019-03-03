package ru.mail.polis.open.task1;

/**
 * @author mikhail.nechaev
 * Since 25/02/2019
 */
interface IFizzBuzz{

    void print ( int from, int to);

}

public class FizzBuzz implements IFizzBuzz {

    public static void main(String[] args) {
        FizzBuzz m = new FizzBuzz();
        m.print(1,100);
    }

    @Override
    public void print(int from, int to) {
        for ( int i = from; i < to+1; i++){
            String str = "";
            if( i % 3 == 0 ) {
                str += "Fizz";
            }
            if( i % 5 == 0) {
                str += "Buzz";
            }
            if (str.length() == 0){
                str += Integer.toString(i);
            }
            System.out.println(str);

        }
    }
}