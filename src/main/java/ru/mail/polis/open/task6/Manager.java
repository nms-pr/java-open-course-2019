package ru.mail.polis.open.task6;

public class Manager {

    void closeLibrary() {
        Library.close();
    }

    void openLibrary() {
        Library.open();
    }

    void addBook(String name, int section) {
        Book newBook = new Book(name, section);
        LibraryStorage.addNewBook(newBook);
    }

    void addBook(Book book) {
        LibraryStorage.addNewBook(book);
    }

    void removeBook(String name, int section) {
        LibraryStorage.removeBook(new Book(name, section));
    }

    void removeBook(int id, int section) {
        LibraryStorage.removeBook(id, section);
    }

    void removeBook(Book book) {
        LibraryStorage.removeBook(book);
    }

}
