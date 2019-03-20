package ru.mail.polis.open.lectures.l4.inner;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedPointArrayMain {

    public static void main(String[] args) {
        LimitedPointArray limitedPointArray = new LimitedPointArray(3);
        limitedPointArray.set(0, new LimitedPointArray.Point());
        limitedPointArray.set(1, new LimitedPointArray.Point(1, 2));
        limitedPointArray.set(2, 2, 3);
    }
}
