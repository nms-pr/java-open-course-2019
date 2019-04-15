package ru.mail.polis.open.task6;

public interface IVisitor {

    void takeBook(long id);

    void takeBookByTitle(String... bookTitle);

    void takeBookByGenre(String... bookGenre);

    void returnBook(Book... book);
}
