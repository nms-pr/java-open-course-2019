package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTest {

    Library library = Library.getInstance();
    BookShelf bookShelf = BookShelf.getShelf();
    Manager manager = new Manager("Manager");
    Customer customer = new Customer("Customer");
    Librarian librarian = new Librarian("Librarian");

    @BeforeEach
    void beforeAll() {
        BookShelf.setBooksCount(0);
    }

    @Test
    void takeOneBook() {
        manager.openLib();
        manager.bringBookToLibrary("Book1");
        manager.bringBookToLibrary("Book2");
        manager.bringBookToLibrary("Book3");
        librarian.addBookToShelf("Genre1", "Book1", 1);
        librarian.addBookToShelf("Genre1", "Book2", 2);
        librarian.addBookToShelf("Genre2", "Book3", 3);
        customer.askForBooks(librarian);
        customer.takeBook("Genre1", "Book2", librarian, customer);
        librarian.getClientByName(customer.getName()).hasDebts();
        assertEquals(1, customer.getBookList().size());
    }

    @Test
    void returnTwoBooks() {
        manager.openLib();
        manager.bringBookToLibrary("Book1");
        manager.bringBookToLibrary("Book2");
        manager.bringBookToLibrary("Book3");
        manager.bringBookToLibrary("Book4");
        librarian.addBookToShelf("Genre1", "Book1", 1);
        librarian.addBookToShelf("Genre1", "Book2", 2);
        librarian.addBookToShelf("Genre2", "Book3", 3);
        librarian.addBookToShelf("Genre2", "Book4", 4);
        customer.askForBooks(librarian);
        customer.takeBook("Genre1", "Book1", librarian, customer);
        customer.takeBook("Genre2", "Book4", librarian, customer);
        customer.returnBook("Book1");
        customer.returnBook("Book4");
        librarian.addBookToShelf("Genre1", "Book1", 1);
        librarian.addBookToShelf("Genre2", "Book4", 4);
        assertEquals(4, BookShelf.booksCount);
    }
}
