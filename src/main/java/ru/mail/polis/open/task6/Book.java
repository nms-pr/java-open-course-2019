package ru.mail.polis.open.task6;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

public class Book {

    private int id;
    private String bookName;
    private int placeAtShelf;
    private Calendar takeBook;
    private Calendar returnBook;
    private String customerName;
    private boolean isReturned = true;

    public Book(String bookName) {
        this.id = new Random().nextInt(1000);
        this.bookName = bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                placeAtShelf == book.placeAtShelf &&
                isReturned == book.isReturned &&
                Objects.equals(bookName, book.bookName) &&
                Objects.equals(takeBook, book.takeBook) &&
                Objects.equals(returnBook, book.returnBook) &&
                Objects.equals(customerName, book.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, placeAtShelf, takeBook, returnBook, customerName, isReturned);
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getPlaceAtShelf() {
        return placeAtShelf;
    }

    public Calendar getTakeBook() {
        return takeBook;
    }

    public Calendar getReturnBook() {
        return returnBook;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setPlaceAtShelf(int placeAtShelf) {
        this.placeAtShelf = placeAtShelf;
    }

    public void setTakeBook(Calendar takeBook) {
        this.takeBook = takeBook;
    }

    public void setReturnBook(Calendar returnBook) {
        this.returnBook = returnBook;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "id:" + id + ", bookName:" + bookName;
    }

}
