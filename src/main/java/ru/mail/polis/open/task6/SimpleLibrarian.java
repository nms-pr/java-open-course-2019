package ru.mail.polis.open.task6;

import java.util.List;

public class SimpleLibrarian implements Librarian {

    BorrowableLibrary library;

    SimpleLibrarian(BorrowableLibrary library) {
        this.library = library;
    }

    @Override
    public void takeBack(Book book) throws LibraryException {
        library.returnBook(book);
    }

    @Override
    public Book giveOut(String title, Visitor visitor) throws LibraryException {
        Book book = library.searchForBook(title);
        if (book != null) {
            library.takeBook(book, visitor);
        }
        return book;
    }

    @Override
    public void notifyTakers() {
        List<Visitor> visitors = library.getCurrentTakers();
        for (Visitor v : visitors) {
            v.notifyAboutBooks();
        }
    }

}
