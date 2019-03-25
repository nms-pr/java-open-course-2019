package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class PrimitiveVars41 {

    public static void main(String[] args) {
        double d = 352115.456D; //101_0101_1111_0111_0011
        System.out.println((byte) d); //115
        System.out.println((short) d); //24435
        System.out.println((char) d); //彳
        System.out.println((int) d); //352115
        System.out.println((long) d); //352115
        System.out.println((float) d); //352115.47
    }
}
