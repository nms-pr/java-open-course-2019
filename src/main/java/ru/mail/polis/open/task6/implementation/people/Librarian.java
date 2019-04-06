package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForLibrarian;

import java.util.HashSet;
import java.util.Set;

public class Librarian extends Person {

    private final Person self;
    private LibraryForLibrarian library;

    public Librarian(Person self) {
        super(self.getFirstName(), self.getLastName());
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

    Book lendBook(Book book) {
        library.getBookProvider().lendBook(book);
        return book;
    }

    Set<Book> lendAllBooks(Set<Book> books) {

        for (Book book : books) {
            lendBook(book);
        }
        return books;
    }

    void retrieveBook(Book book) {

        library.getBookProvider().retrieveBook(book);
    }
}
