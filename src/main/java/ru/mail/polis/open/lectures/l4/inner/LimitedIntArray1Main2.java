package ru.mail.polis.open.lectures.l4.inner;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray1Main2 {

    public static void main(String[] args) {
        final int[] data = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        LimitedIntArray1 lia2 = new LimitedIntArray1(data);
        final int size = lia2.size();
        final int halfSize = size / 2;
        for (int i = 0; i < halfSize; i++) {
            System.out.print(lia2.get(i) + " ");
        }
        lia2.set(halfSize, -100);
        for (int i = halfSize; i < size; i++) {
            System.out.print(lia2.get(i) + " ");
        }
        //0 1 2 3 4 -100 6 7 8 9
    }
}
