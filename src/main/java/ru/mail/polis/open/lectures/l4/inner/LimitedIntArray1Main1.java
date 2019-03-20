package ru.mail.polis.open.lectures.l4.inner;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray1Main1 {

    public static void main(String[] args) {
        final int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        LimitedIntArray1 lia1 = new LimitedIntArray1(data);
        for (int i = 0; i < lia1.size(); i++) {
            System.out.print(lia1.get(i) + " ");
        }
        //0 1 2 3 4 5 6 7 8 9
    }
}
