package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class ClassWithMethod2 {

    private String varS = "ABC";

    private String modifyS(String varS) {
        this.varS = "BCD";
        return varS + "ED";
    }

    public static void main(String[] args) {
        ClassWithMethod2 cwm = new ClassWithMethod2();
        System.out.println(cwm.varS); // ABC

        String varS = cwm.varS;
        String res = cwm.modifyS(varS);

        System.out.println(varS); // ABC
        System.out.println(cwm.varS); // BCD
        System.out.println(res); // ABCED
    }
}
