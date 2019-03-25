package ru.mail.polis.open.lectures.l4.classes;

/**
 * @author mikhail.nechaev
 * Since 18/03/2019
 */
public class Rectangle1 {

    private int width;
    private int height;

    public int getSquare() {
        return width * height;
    }

    public static void main(String[] args) {
        Rectangle1 rectangle1 = new Rectangle1();
        rectangle1.width = 1;
        rectangle1.height = 2;
        System.out.println(rectangle1.getSquare());
    }
}
