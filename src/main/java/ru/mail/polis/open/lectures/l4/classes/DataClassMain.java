package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
class DataClassMain {

    public static void main(String[] args) {
        DataClass dataClass = new DataClass();

        System.out.println(dataClass.var1); //== 0

        System.out.println(dataClass.var2); //== 0. Wrong!
        System.out.println(DataClass.var2); //== 0. OK!

        dataClass.var1 = 5;
        dataClass.var2 = 10; //Wrong!
        DataClass.var2 = 11; //OK!

        System.out.println(dataClass.var1); //== 5
        System.out.println(DataClass.var2); //== 11
    }
}
