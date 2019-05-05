package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LibrarianImplTest {
    private static LibrarianImpl librarian;
    private static CustomerImpl customer;
    private static ManagerImpl manager;
    private static Book book;
    private static Book book1;
    private static Book fakeBook;

    @BeforeEach
    void beforeStart() {
        librarian = new LibrarianImpl(
                "David",
                "Smith",
                "Peter",
                (byte) 22
        );
        manager = new ManagerImpl("David",
                "Smith",
                "Peter",
                (byte) 22);
        customer = new CustomerImpl("Natasha", "Romanov", "Ivan", (byte) 31);
        manager.openLibrary();

    }

    @Test
    void checkGiveBookByName() {
        book = Library.getBooks().get(0);
        fakeBook = new Book(9, 9, "dd", "ff");
        assertEquals(
                book,
                librarian.giveBook("War and Peace", customer));
        assertThrows(
                IllegalArgumentException.class, () -> librarian.giveBook(null, customer)
        );
        assertThrows(
                IllegalArgumentException.class, () -> librarian.giveBook(fakeBook.name, customer));
    }

    @Test
    void checkGiveBookById() {
        book = Library.getBooks().get(0);
        fakeBook = new Book(9, 9, "dd", "ff");
        assertEquals(book, librarian.giveBook(book.id, customer));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(null, customer));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(fakeBook.getId(), customer));
    }

    @Test
    void checkPutBook() {
        book = Library.getBooks().get(0);
        librarian.putBook(librarian.giveBook(book.name, customer), customer);
        assertEquals(book, Library.getShelfsInLibrary().get(book.shelf).getBooksOnShelf().get(book.shelf));
        assertThrows(IllegalArgumentException.class, () -> librarian.putBook(null, customer));
    }

    @Test
    void checkFindBook() {
        book = Library.getBooks().get(0);
        book1 = Library.getBooks().get(1);
        assertEquals(book, librarian.findBook(1000187));
        assertEquals(book1, librarian.findBook("Crime and punishment"));
        assertThrows(IllegalArgumentException.class, () -> librarian.findBook(1));
        assertThrows(IllegalArgumentException.class, () -> librarian.findBook("э"));
    }

    @AfterEach
    void end() {
        Library.setStatus(false);
        Library.setCounter();
        Library.getCustomers().clear();
        Library.getBusyBooks().clear();
        Library.getShelfsInLibrary().clear();
        Library.getBooks().clear();
    }
}