package ru.mail.polis.open.task6;

public interface Manager {
    Book add(Book book);
    void remove(Book book);
    void openLibrary();
    void closeLibrary();

}
