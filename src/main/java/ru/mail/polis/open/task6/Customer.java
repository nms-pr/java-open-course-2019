package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Customer extends Person {
    private ArrayList<Book> onHands = new ArrayList<>();

    Customer(String fullName, String sex, int age) {
        super(fullName, sex, age);
    }

    public ArrayList<Book> whatOnHands() {
        return onHands;
    }

    public void getBook(Library library, Book book) {
        library.giveBook(book, this);
    }

    public void returnBook(Library library, Book book) {
        library.returnBook(book, this);
    }

    public ArrayList<Book> whatInStock(Library library) {
        if (library.isOpen()) {
            return library.getAllBooks();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
