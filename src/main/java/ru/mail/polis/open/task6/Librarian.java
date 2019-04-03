package ru.mail.polis.open.task6;

public interface Librarian {
    Book giveBook(String name, String author, VisitorImpl visitor);
    Book giveBook(long ID, VisitorImpl visitor);
    void putBook(Book book, VisitorImpl visitor);
}
