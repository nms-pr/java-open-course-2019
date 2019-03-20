package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class PrimitiveVars42 {

    public static void main(String[] args) {
        //101_0101_1111_0111_0011
        double d = 352115.456D;
        //               111_0011
        System.out.println(Long.toBinaryString((byte) d));
        //     101_1111_0111_0011
        System.out.println(Long.toBinaryString((short) d));
        //     101_1111_0111_0011
        System.out.println(Long.toBinaryString((char) d));
        //101_0101_1111_0111_0011
        System.out.println(Long.toBinaryString((int) d));
    }
}
