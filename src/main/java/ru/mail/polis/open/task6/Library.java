package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Library implements Manager, Librarian {

    private String name;

    public Library(String name) {
        this.name = name;
    }

    @Override
    public boolean addBook(Book book) {
        return false;
    }

    @Override
    public Book issueBook(Book book) {
        return null;
    }

    @Override
    public boolean openInstitution() {
        return false;
    }

    @Override
    public boolean closeInstitution() {
        return false;
    }

    @Override
    public Book bringBook() {
        return null;
    }

    @Override
    public void removeBook(Book book) {

    }
}
