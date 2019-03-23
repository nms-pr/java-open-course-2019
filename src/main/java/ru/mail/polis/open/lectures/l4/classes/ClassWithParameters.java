package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class ClassWithParameters {

    {
        System.out.println("X1");
    }

    static {
        System.out.println("X2");
    }

    int var1 = 1;
    int var2;
    int var3;
    int var4;

    static int var5 = 5;
    static int var6;
    static int var7;
    static int var8;
    static int var9;
    static int var10;

    {
        var2 = 2;
        var6 = 6;
        System.out.println("X3");
    }

    static {
        var7 = 9;
        System.out.println("X4");
    }

    public ClassWithParameters(int var3, int var8) {
        this.var3 = var3;
        ClassWithParameters.var8 = 7;
        System.out.println("X5");
    }

    {
        var4 = 3;
        var9 = 8;
        System.out.println("X6");
    }

    static {
        var10 = 10;
        System.out.println("X7");
    }

    public static void main(String[] args) {
        System.out.println("X8");
        new ClassWithParameters(3, 8);
        System.out.println("X9");
        new ClassWithParameters(3, 8);
        System.out.println("X10");

        //(2-4-7)-8-[1-3-6-5]-9-[1-3-6-5]-10
    }
}
