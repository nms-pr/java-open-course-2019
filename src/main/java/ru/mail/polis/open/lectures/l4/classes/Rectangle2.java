package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle2 {

    private int width;
    private int height;

    public Rectangle2(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getSquare() {
        return width * height;
    }

    public static void main(String[] args) {
        Rectangle2 rectangle1 = new Rectangle2(2, 3);
        System.out.println(rectangle1.getSquare());
    }
}
