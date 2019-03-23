package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class PrimitiveVars15 {

    private static void print(double x) {
        System.out.println("double: " + x);
    }

    public static void main(String[] args) {
        print((byte) 0);
        print((short) 0);
        print((int) 0);
        print((float) 0);
        print((double) 0);
    }
}
