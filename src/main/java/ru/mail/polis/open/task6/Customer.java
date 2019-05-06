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
        onHands.add(book);
    }

    public void returnBook(Library library, Book book) {
        onHands.remove(book);
        library.returnBook(library, book, this);
    }

    public ArrayList<Book> whatInStock(Library library) {
        return library.getAllBooks();
    }
}
