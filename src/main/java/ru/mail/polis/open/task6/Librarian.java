package ru.mail.polis.open.task6;

public interface Librarian {
    Book giveBook(String name, CustomerImpl customer);

    Book giveBook(long id, CustomerImpl customer);

    void putBook(Book book, CustomerImpl customer);
}