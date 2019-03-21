package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle7 {

    private final int width;
    private final int height;
    private final String name;

    public Rectangle7() {
        this(10, 20, "Rectangle7");
    }

    public Rectangle7(String name) {
        this(10, 20, name);
    }

    public Rectangle7(int width) {
        this(width, width, "Rectangle7");
    }

    public Rectangle7(int width, int height) {
        this(width, height, "Rectangle7");
    }

    public Rectangle7(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }
}
