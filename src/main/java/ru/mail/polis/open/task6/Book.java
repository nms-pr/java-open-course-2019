package ru.mail.polis.open.task6;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book {

    private int id;
    private String bookName;
    private int placeAtShelf;
    private Calendar takeBook;
    private Calendar returnBook = new GregorianCalendar();
    private String customerName;

    public Book(int id, String bookName, Calendar takeBook) {
        this.id = id;
        this.bookName = bookName;
        this.takeBook = takeBook;
        returnBook.add(5, 30);
    }

    @Override
    public String toString() {
        return "id:" + id + ", bookName:" + bookName + " takeBookTime:" + takeBook.getTime() + " returnBookTime: " + returnBook.getTime();
    }
}
