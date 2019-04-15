package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class PrimitiveVars31 {

    private static void int_(int x) {
        System.out.println("int: " + x);
    }

    private static void integer_(Integer x) {
        System.out.println("Integer: " + x);
    }

    public static void main(String[] args) {
        int intX = 0;
        Integer integerX = Integer.valueOf(0);

        int_(intX);
        int_(integerX);

        integer_(intX);
        integer_(integerX);
    }
}
