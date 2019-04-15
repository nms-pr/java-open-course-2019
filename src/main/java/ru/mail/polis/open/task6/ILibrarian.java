package ru.mail.polis.open.task6;

public interface ILibrarian {

    Book giveBook(String bookTitle, Visitor visitor);

    Book giveBook(long id, Visitor visitor);

    void putBook(Book book, Visitor visitor);
}
