package ru.mail.polis.open.task6;

import java.util.ArrayList;

public class Visitor {

    private String firstName;
    private String secondName;
    private ArrayList<Book> takenBooks;

    Visitor(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.takenBooks = new ArrayList<>();
    }

    void getBooks(int[] id, int[] section) {
        ArrayList<Book> receivedBooks = Librarian.giveBooks(id, section, this);
        if (receivedBooks != null) {
            takenBooks.addAll(receivedBooks);
        }
    }

    void getBooks(String[] name, int[] section) {
        ArrayList<Book> receivedBooks = Librarian.giveBooks(name, section, this);
        if (receivedBooks != null) {
            takenBooks.addAll(receivedBooks);
        }
    }

    void giveBook(Book book) {
        Librarian.receiveBook(book, this);
        takenBooks.remove(book);
    }

    void giveBook(int index) {
        Librarian.receiveBook(takenBooks.get(index), this);
        takenBooks.remove(index);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public ArrayList<Book> getTakenBooks() {
        return takenBooks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Visitor visitor = (Visitor) obj;
        return visitor.getFirstName().equals(this.firstName)
                && visitor.getSecondName().equals(this.secondName);
    }
}
