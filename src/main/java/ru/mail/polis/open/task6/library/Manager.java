package ru.mail.polis.open.task6.library;


public class Manager {
    private String name;
    private Library library;

    public Manager(String name) {
        this.name = name;
    }

    public void linkWithLibrary(Library library) {
        this.library = library;
    }


    public void put(Book book) {
        if (library.isOpen()) {
            library.getShelf().add(book);
            return;
        } throw new IllegalArgumentException("The library is closed");
    }


    public void remove (Book book) {
        if (library.isOpen()) {
            library.getShelf().remove(book);
            return;
        } throw new IllegalArgumentException("The library is closed");
    }

    public void open() {
        library.open();
    }

    public void close() {
        library.close();
    }

}
