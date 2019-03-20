package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle8 {

    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 10;
    private static final String DEFAULT_NAME = "Rectangle8";

    private final int width;
    private final int height;
    private final String name;

    public Rectangle8() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_NAME);
    }

    public Rectangle8(String name) {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_NAME);
    }

    public Rectangle8(int width) {
        this(width, width, DEFAULT_NAME);
    }

    public Rectangle8(int width, int height) {
        this(width, height, DEFAULT_NAME);
    }

    public Rectangle8(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }
}
