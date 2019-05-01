package ru.mail.polis.open.task6;

public class Manager {
    private String name;
    private Library library = Library.getInstance();

    public Manager(String name) {
        this.name = name;
    }

    public void openLib() {
        if (Library.isOpened) {
            System.out.println("Библиотека уже открыта");
        } else {
            Library.isOpened = true;
        }
    }

    public void closeLib() {
        if (!Library.isOpened) {
            System.out.println("Библиотека уже закрыта");
        } else {
            Library.isOpened = false;
        }
    }

    public void bringBookToLibrary(String bookName) {
        library.addBook(bookName);
    }

    public void removeBookFromLibrary(String bookName) {
        library.removeBook(bookName);
    }
}
