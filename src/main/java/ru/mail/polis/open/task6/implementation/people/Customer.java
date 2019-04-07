package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    public void notifyAboutBook(String message) {

        System.out.println(message);
    }

    public void takeBook(Book book) {

        this.books.add(library.getLibrarian().lendBook(this, book));
    }

    public void takeAnyBook() {

        Librarian librarian = library.getLibrarian();

        Book[] books = librarian.getAllBooks().toArray(Book[]::new);
        Random r = new Random();
        Book bookToTake = books[r.nextInt(books.length)];

        librarian.lendBook(this, bookToTake);
        this.books.add(bookToTake);
    }

    public void takeBooksByCategory(Category category) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                this,
                librarian.getBooksByCategory(category)
            )
        );
    }

    public void takeBooksByAuthor(String author) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                this,
                librarian.getBooksByAuthor(author)
            )
        );
    }

    public Set<Book> getBooksByCategory(Category category) {

        return library.getLibrarian().getBooksByCategory(category);
    }

    public Set<Book> getBooksByAuthor(String author) {

        return library.getLibrarian().getBooksByAuthor(author);
    }

    public void readBooks() {

        books.forEach(book -> System.out.println(book + ". What a nice book!"));
    }

    public void retrieveBook(Book book) {

        library.getLibrarian().retrieveBook(this, book);
    }

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
