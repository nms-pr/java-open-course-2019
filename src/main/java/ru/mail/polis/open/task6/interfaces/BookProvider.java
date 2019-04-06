package ru.mail.polis.open.task6.interfaces;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.BookInfo;

public interface BookProvider {

    BookInfo lendBook(Book book);
    BookInfo retrieveBook(Book book);
    Iterable<Book> getAllBooks();
}
