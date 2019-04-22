package ru.mail.polis.open.task6;

public interface Visitor {

    void returnBook(Book book);

    void getBook(String bookName);

    void prolongBook(Book bookInLib);
}
