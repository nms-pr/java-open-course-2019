package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.BookInfo;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForLibrarian;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Librarian extends Person {

    private final long WEEK = 604800000;

    private final Person self;
    private LibraryForLibrarian library;

    public Librarian(Person self) {
        this.self = self;
    }

    public Person getSelf() {
        return self;
    }

    public void assignToLibrary(LibraryForLibrarian library) {
        this.library = library;
    }

    Set<Book> getAllBooks() {
        return library.getBookProvider().getAllBooks();
    }

    Set<Book> getBooksByCategory(Category category) {

        Set<Book> allBooks = getAllBooks();

        Set<Book> filteredBooks = new HashSet<>();

        allBooks.forEach(book -> {
            if (book.getCategory() == category) {
                filteredBooks.add(book);
            }
        });

        return filteredBooks;
    }

    Set<Book> getBooksByAuthor(String author) {
        Set<Book> allBooks = getAllBooks();

        Set<Book> filteredBooks = new HashSet<>();

        allBooks.forEach(book -> {
            if (book.getAuthor().equals(author)) {
                filteredBooks.add(book);
            }
        });

        return filteredBooks;
    }

    Book lendBook(Customer customer, Book book) {
        BookInfo bookInfo = library.getBookProvider().lendBook(book);

        Date bebinTime = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + WEEK);
        bookInfo.addToHistory(customer, bebinTime, endDate);
        return book;
    }

    Set<Book> lendAllBooks(Customer customer, Set<Book> books) {

        for (Book book : books) {
            lendBook(customer, book);
        }
        return books;
    }

    void retrieveBook(Book book) {

        library.getBookProvider().retrieveBook(book);
    }

    @Override
    public String getFirstName() {
        return self.getFirstName();
    }

    @Override
    public String getLastName() {
        return self.getLastName();
    }
}
