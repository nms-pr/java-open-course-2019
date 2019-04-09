package ru.mail.polis.open.task6;

import java.util.List;

public class LibrarianImpl implements Librarian {

    private List<Book> books;
    private List<Book> booksAll;

    public LibrarianImpl(Library library) {
        this.books = library.getBooksAvailable();
        this.booksAll = library.getBooksAll();
    }

    @Override
    public boolean addBook(Book book) {
        if (booksAll.contains(book)) {
            books.add(book);
            return true;
        }
        return false;
    }

    @Override
    public Book issueBook(Book book) {
        if(books.contains(book)){
            books.remove(book);
            return book;
        }
        return null;
    }
}
