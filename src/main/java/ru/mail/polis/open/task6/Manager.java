package ru.mail.polis.open.task6;

public class Manager extends Person {
    Manager(String fullName, String sex, int age) {
        super(fullName, sex, age);
    }

    public void addBook(Library library, int id, String title, int section, int shelfPlace) {
        library.addBook(id, title, section, shelfPlace);
    }

    public void removeBook(Library library, Book book) {
        library.removeBook(book);
    }

    public void closeLibrary(Library library) {
        library.closeLibrary();
    }

    public void openLibrary(Library library) {
        library.openLibrary();
    }
}
