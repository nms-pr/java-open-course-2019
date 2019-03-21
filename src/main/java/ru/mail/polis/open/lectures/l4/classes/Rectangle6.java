package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle6 {

    private final int width;
    private final int height;
    private final String name;

    public Rectangle6() {
        this.width = 10;
        this.height = 20;
        this.name = "Rectangle6";
    }

    public Rectangle6(String name) {
        this.width = 10;
        this.height = 20;
        this.name = name;
    }

    public Rectangle6(int width) {
        this.width = width;
        this.height = width;
        this.name = "Rectangle6";
    }

    public Rectangle6(int width, int height) {
        this.width = width;
        this.height = height;
        this.name = "Rectangle6";
    }

    public Rectangle6(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }
}
