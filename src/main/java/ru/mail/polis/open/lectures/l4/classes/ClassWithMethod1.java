package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class ClassWithMethod1 {

    private String varS = "ABC";

    private String modifyS(String varS) {
        varS = "BCD";
        return varS + "ED";
    }

    public static void main(String[] args) {
        ClassWithMethod1 cwm = new ClassWithMethod1();
        System.out.println(cwm.varS); // ABC

        String varS = cwm.varS;
        String res = cwm.modifyS(varS);

        System.out.println(cwm.varS); //ABC
        System.out.println(varS); //ABC
        System.out.println(res); //BCDED
    }
}
