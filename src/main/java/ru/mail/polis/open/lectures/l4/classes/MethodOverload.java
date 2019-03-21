package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class MethodOverload {

    public void method() {
        System.out.println("1");
    }

    /*
    public void method(int x) {
        System.out.println("2");
    }
    */

    public void method(int x, int y) {
        System.out.println("3");
    }

    /*
    public int method(int x) {
        System.out.println("4");
        return 0;
    }
    */

    public int method(int x, int y, int z) {
        System.out.println("5");
        return 0;
    }

    public int method(int... x) {
        System.out.println("6");
        return x.length;
    }

}
