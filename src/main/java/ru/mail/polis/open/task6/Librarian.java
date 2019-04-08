package ru.mail.polis.open.task6;

public interface Librarian {
    public Book giveBook(String name, CustomerImpl customer);

    public Book giveBook(long id, CustomerImpl customer);

    void putBook(Book book, CustomerImpl customer);
}
