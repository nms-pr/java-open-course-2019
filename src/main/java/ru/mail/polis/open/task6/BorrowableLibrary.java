package ru.mail.polis.open.task6;

import java.util.List;
import ru.mail.polis.open.task6.BookInfo.TakerInfo;

public interface BorrowableLibrary {
    Book searchForBook(String title);

    void takeBook(Book book, Visitor visitor) throws LibraryException;

    void returnBook(Book book) throws LibraryException;

    List<Book> getAvailableBooks();

    List<TakerInfo> getTakersHistory(Book book);

    List<Visitor> getCurrentTakers();
}
