package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle9 {

    private final int width;
    private final int height;
    private String name;

    public Rectangle9(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle9(int width, int height, String name) {
        this(width, height);
        this.name = name;
    }
}
