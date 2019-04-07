package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Person, who can take books from library
 * Works as wrapper around Person class
 */
public class Customer extends Person {

    private Person self;
    private LibraryForCustomer library;
    private List<Book> books;

    public Customer(Person self, LibraryForCustomer library) {

        this.self = self;
        this.library = library;
        books = new ArrayList<>();
    }

    public Person getSelf() {
        return self;
    }

    @Override
    public String getFirstName() {
        return self.getFirstName();
    }

    @Override
    public String getLastName() {
        return self.getLastName();
    }

    /**
     * Invoked when librarian has to notify customer that books were not returned
     *
     * @param message - message from librarian
     */
    void notifyAboutBook(String message) {

        System.out.println(message);
    }

    /**
     * Take book from library
     *
     * @param book - book to take
     */
    public void takeBook(Book book) {

        this.books.add(library.getLibrarian().lendBook(this, book));
    }

    /**
     * Take random book from libraty
     */
    public void takeAnyBook() {

        Librarian librarian = library.getLibrarian();

        Book[] books = librarian.getAllBooks().toArray(Book[]::new);
        Random r = new Random();
        Book bookToTake = books[r.nextInt(books.length)];

        librarian.lendBook(this, bookToTake);
        this.books.add(bookToTake);
    }

    /**
     * Take all books that match specified Category
     * @param category - category
     */
    public void takeBooksByCategory(Category category) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                this,
                librarian.getBooksByCategory(category)
            )
        );
    }

    /**
     * Take all books that match specified author
     *
     * @param author - author
     */
    public void takeBooksByAuthor(String author) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                this,
                librarian.getBooksByAuthor(author)
            )
        );
    }

    /**
     * Returns filtered set of books
     *
     * @return set of books filtered by category
     */
    public Set<Book> getBooksByCategory(Category category) {

        return library.getLibrarian().getBooksByCategory(category);
    }

    /**
     * Returns filtered set of books
     *
     * @return set of books filtered by author
     */
    public Set<Book> getBooksByAuthor(String author) {

        return library.getLibrarian().getBooksByAuthor(author);
    }

    public void readBooks() {

        books.forEach(book -> System.out.println(book + ". What a nice book!"));
    }

    /**
     * Returns book to library
     *
     * @param book - book to return
     */
    public void retrieveBook(Book book) {

        if (!books.contains(book)) {
            throw new IllegalArgumentException("This book does not belong to this customer");
        }

        library.getLibrarian().retrieveBook(this, book);
    }

    /**
     * Returns all books to library
     */
    public void retrieveAllBooks() {

        for (Book book : books) {
            retrieveBook(book);
        }
        books.clear();
    }

    @Override
    public String toString() {
        return "customer "  + self.toString();
    }
}
