package ru.mail.polis.open.task6.implementation.people;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.implementation.BookShelf;
import ru.mail.polis.open.task6.implementation.Library;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    private static Manager manager;
    private static BookShelf bookShelf;
    private static Librarian librarian;
    private static Library library;

    @BeforeEach
    void createManager() {
        bookShelf = new BookShelf();
        manager = new Manager(new Person("name", "surname"));
        librarian = new Librarian(new Person("name", "surname"));
        library = new Library(bookShelf, manager, librarian);
        librarian.assignToLibrary(library);
        manager.assignToLibrary(library);
    }

    @Test
    void addBook() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);

        assertEquals(Set.of(book1), bookShelf.getAllBooks());

        Book book2 = new Book("n2", "a2", Category.PROGRAMMING);

        manager.addBook("n2", "a2", Category.PROGRAMMING);

        assertEquals(Set.of(book1, book2), bookShelf.getAllBooks());
    }

    @Test
    void deleteBook_WorksCorrectly() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        manager.deleteBook(book1);

        assertEquals(Set.of(), bookShelf.getAllBooks());
    }

    @Test
    void deleteBook_ReturnsFalseWhenBookIsNotInStock() {
        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        librarian.lendBook(book1);

        assertThrows(NoSuchElementException.class, () -> manager.deleteBook(book1));
    }

    @Test
    void deleteBook_ThrowsWhenBookDoesNotExist() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        assertThrows(NoSuchElementException.class, () -> manager.deleteBook(book1));
    }

    @Test
    void deleteBookIfPresent_ReturnsTrueWhenBookIsInStock() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());

        assertTrue(manager.deleteBookIfPresent(book1));

        assertEquals(Set.of(), bookShelf.getAllBooks());
    }


    @Test
    void deleteBookIfPresent_ReturnsFalseWhenBookIsNotInStock() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        manager.addBook(book1);
        assertEquals(Set.of(book1), bookShelf.getAllBooks());
        librarian.lendBook(book1);

        assertFalse(manager.deleteBookIfPresent(book1));
    }


    @Test
    void deleteBookIfPresent_ReturnsFalseWhenBookDoesNotExists() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);

        assertFalse(
            manager.deleteBookIfPresent(book1));
    }

    @Test
    void openLibrary() {

        manager.openLibrary();

        assertTrue(library.isOpened());
    }

    @Test
    void closeLibrary() {

        manager.openLibrary();
        assertTrue(library.isOpened());
        manager.closeLibrary();

        assertFalse(library.isOpened());
    }
}