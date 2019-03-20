package ru.mail.polis.open.lectures.l4.inner;

import java.util.Iterator;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray2Main1 {

    public static void main(String[] args) {
        final int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        LimitedIntArray2 lia2 = new LimitedIntArray2(data);
        for (int x : lia2) {
            System.out.print(x + " ");
        }
        //0 1 2 3 4 5 6 7 8 9
    }
}
