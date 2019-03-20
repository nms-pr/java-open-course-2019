package ru.mail.polis.open.lectures.l4.inner;

import java.util.Iterator;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray2Main2 {

    public static void main(String[] args) {
        final int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        LimitedIntArray2 lia2 = new LimitedIntArray2(data);

        int id = 0;
        for (int x : lia2) {
            System.out.print(x + " ");
            if (id == 5) {
                lia2.set(id, -100);
            }
            id++;
        }
        //0 1 2 3 4 5 java.util.ConcurrentModificationException
    }
}
