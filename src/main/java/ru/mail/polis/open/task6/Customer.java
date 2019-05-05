package ru.mail.polis.open.task6;

public interface Customer {
    void takeBook(String name);

    void takeBook(long id);

    void takeBook(String[] names);

    void takeBook(long[] ids);

    void returnBook(Book book);

    void returnBook(Book[] books);

}