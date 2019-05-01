package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> library;
    private static Library instance = new Library();
    static boolean isOpened;

    private Library() {
        library = new ArrayList<>();
    }

    public static Library getInstance() {
        return instance;
    }

    public void showLib() {
        System.out.println(library);
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public List<Book> getLibrary() {
        return library;
    }

    protected boolean addBook(String bookName) {
        if (library.contains(getBookByName(bookName))) {
            return false;
        } else {
            library.add(new Book(bookName));
            return true;
        }
    }

    void removeBook(String bookName) {
        library.remove(getBookByName(bookName));
    }

    public Book getBookByName(String bookName) {
        for (Book book : library) {
            if (book.getBookName().equals(bookName)) {
                return book;
            }
        }
        return null;
    }
}


