package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.BookInfo;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.implementation.book.HistoryEntry;
import ru.mail.polis.open.task6.interfaces.BookProvider;
import ru.mail.polis.open.task6.interfaces.LibraryForLibrarian;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Person, who can lend and retrieve books and provide information about them
 * Works as wrapper around Person class
 */
public class Librarian extends Person {

    // One week in ms
    private static final long LEND_TIME = 604800000;

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

    /**
     * @return - all (unique) books available in library at the moment
     */
    public Set<Book> getAllBooks() {

        ifLibraryClosed();

        BookProvider bookProvider  = library.getBookProvider();
        return bookProvider.getAllBooks()
            .stream()
            .filter(book ->
                bookProvider.getBookInfo(book).getInStock() > 0)
            .collect(Collectors.toSet());
    }

    /**
     * @return all (unique) books available in library at the moment,
     * that match predicate
     */
    public Set<Book> getBooksWhen(Predicate<Book> predicate) {

        return getAllBooks()
            .stream()
            .filter(predicate)
            .collect(Collectors.toSet());
    }


    /**
     * @return - all (unique) books available in library at the moment,
     * filtered by category
     */
    public Set<Book> getBooksByCategory(Category category) {

        return getBooksWhen(book -> book.getCategory() == category);
    }

    /**
     * @return - all (unique) books available in library at the moment,
     * filtered by author
     */
    public Set<Book> getBooksByAuthor(String author) {

        return getBooksWhen(book -> book.getAuthor().equals(author));
    }

    /**
     * Lends
     * @param book - book
     * to
     * @param customer - customer
     * @return book
     */
    public Book lendBook(Customer customer, Book book) {

        ifLibraryClosed();
        BookInfo bookInfo = library.getBookProvider().lendBook(book);

        Date beginTime = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + LEND_TIME);
        bookInfo.addToHistory(customer, beginTime, endDate);
        return book;
    }

    /**
     * Lends all
     * @param books - books
     * to
     * @param customer - customer
     * @return books
     */
    public Set<Book> lendAllBooks(Customer customer, Set<Book> books) {

        ifLibraryClosed();
        for (Book book : books) {
            lendBook(customer, book);
        }
        return books;
    }

    /**
     * Retrieves book
     * @param book - book
     * from
     * @param customer - customer
     */
    public void retrieveBook(Customer customer, Book book) {

        ifLibraryClosed();

        Optional<HistoryEntry> first = library.getBookProvider().retrieveBook(book).getHistory()
            .stream()
            .filter(entry ->
                entry.getCustomer().equals(customer))
            .findFirst();

        if (first.isEmpty()) {
            throw new IllegalArgumentException("You have not borrowed this book");
        }

        first.get().doReturn();
    }

    /**
     * Notifies all customers, who are having books at the moment about it
     */
    public void notifyAllCustomersWithBooks() {

        ifLibraryClosed();
        BookProvider provider = library.getBookProvider();

        Set<Book> books = provider.getAllBooks();

        for (Book book : books) {
            for (HistoryEntry entry : provider.getBookInfo(book).getHistory()) {
                if (!entry.isReturned()) {
                    entry.getCustomer().notifyAboutBook(
                        "Dear " + entry.getCustomer() + "! Please, return book " + book
                    );
                }
            }
        }
    }

    /**
     * Checks whether library is closed
     * If so, throws an exception
     */
    private void ifLibraryClosed() {

        if (!library.isOpened()) {
            throw new IllegalStateException("Library is closed( Come back later!");
        }
    }

    @Override
    public String getFirstName() {
        return self.getFirstName();
    }

    @Override
    public String getLastName() {
        return self.getLastName();
    }

    @Override
    public String toString() {
        return "librarian" + self.toString();
    }
}
