package ru.mail.polis.open.lectures.l4.inner;

import java.util.Objects;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedPointArray {

    private static final int DEFAULT_X1 = 10;
    private static final int DEFAULT_X2 = 20;

    private final Point[] data;

    public LimitedPointArray(int size) {
        data = new Point[size];
    }

    public int size() {
        return data.length;
    }

    public Point get(int index) {
        Objects.checkIndex(index, data.length);
        return data[index];
    }

    public void set(int index, Point value) {
        Objects.checkIndex(index, data.length);
        data[index] = value;
    }

    public void set(int index, int x1, int x2) {
        Objects.checkIndex(index, data.length);
        data[index] = new Point(x1, x2);
    }

    public static class Point {
        int x1;
        int x2;

        public Point() {
            x1 = DEFAULT_X1;
            x2 = LimitedPointArray.DEFAULT_X2;
        }

        public Point(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }
}
