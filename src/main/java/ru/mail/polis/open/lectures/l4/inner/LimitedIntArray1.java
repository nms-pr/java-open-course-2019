package ru.mail.polis.open.lectures.l4.inner;

import java.util.Objects;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray1 {

    private final int[] data;

    public LimitedIntArray1(int size) {
        data = new int[size];
    }

    public LimitedIntArray1(int[] data) {
        this.data = data;
    }

    public int size() {
        return data.length;
    }

    public int get(int index) {
        Objects.checkIndex(index, data.length);
        return data[index];
    }

    public void set(int index, int value) {
        Objects.checkIndex(index, data.length);
        data[index] = value;
    }
}
