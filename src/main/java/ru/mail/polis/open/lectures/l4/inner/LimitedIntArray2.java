package ru.mail.polis.open.lectures.l4.inner;

import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class LimitedIntArray2 implements Iterable<Integer> {

    private final int[] data;
    private int modCount;

    public LimitedIntArray2(int size) {
        data = new int[size];
    }

    public LimitedIntArray2(int[] data) {
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
        modCount++;
    }

    public class Iter implements Iterator<Integer> {

        int position;
        //int modCount = LimitedIntArray2.this.modCount;
        int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return position < data.length;
        }

        @Override
        public Integer next() {
            //if (modCount != LimitedIntArray2.this.modCount)
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (position >= data.length) {
                throw new NoSuchElementException();
            }
            return data[position++];
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iter();
    }
}
