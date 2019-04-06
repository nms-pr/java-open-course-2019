package ru.mail.polis.open.task6.interfaces;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.BookInfo;

import java.util.Set;

public interface BookProvider {

    BookInfo lendBook(Book book);
    BookInfo retrieveBook(Book book);
    Set<Book> getAllBooks();
    BookInfo getBookInfo(Book book);
}
