package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForCustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void takeAnyBook() {

        Librarian librarian = library.getLibrarian();

        Book[] books = librarian.getAllBooks().toArray(Book[]::new);
        Random r = new Random();
        Book bookToTake = books[r.nextInt(books.length)];

        librarian.lendBook(bookToTake);
        this.books.add(bookToTake);
    }

    public void takeBookByCategory(Category category) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                librarian.getBooksByCategory(category)
            )
        );
    }

    public void takeBookByAuthor(String author) {

        Librarian librarian = library.getLibrarian();

        books.addAll(
            librarian.lendAllBooks(
                librarian.getBooksByAuthor(author)
            )
        );
    }

    public void readBooks() {

        books.forEach(book -> System.out.println(book.toString() + "\n\n"));
    }

    public void retrieveBook(Book book) {

        library.getLibrarian().retrieveBook(book);
    }

    public void retrieveAllBooks() {

        for (Book book : books) {
            retrieveBook(book);
        }
        books.clear();
    }
}
