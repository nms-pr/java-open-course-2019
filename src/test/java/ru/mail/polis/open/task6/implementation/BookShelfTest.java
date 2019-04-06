package ru.mail.polis.open.task6.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfTest {

    private BookShelf bookShelf;

    @BeforeEach
    void createBookShelf() {
        bookShelf = new BookShelf();
    }

    @Test
    void getAllBooks_EmptyAtTheBeginning() {
        assertEquals(Set.of(), bookShelf.getAllBooks());
    }

    @Test
    void getAllBooks_WorksCorrectly() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);
        Book book2 = new Book("n2", "a2", Category.PROGRAMMING);
        Book book3 = new Book("n3", "a3", Category.PROGRAMMING);
        Book book4 = new Book("n4", "a4", Category.PROGRAMMING);
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        bookShelf.addBook(book3);
        bookShelf.addBook(book4);

        assertEquals(Set.of(book1, book2, book3, book4), bookShelf.getAllBooks());
    }

    @Test
    void addBook_AddNewBook() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book1);

        assertEquals(Set.of(book1), bookShelf.getAllBooks());

        Book book2 = new Book("n2", "a2", Category.PROGRAMMING);
        bookShelf.addBook(book2);

        assertEquals(Set.of(book1, book2), bookShelf.getAllBooks());
        assertEquals(1, bookShelf.getBookInfo(book1).getTotal());
        assertEquals(1, bookShelf.getBookInfo(book2).getTotal());
    }


    @Test
    void addBook_AddNewInstance() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);

        assertEquals(Set.of(book), bookShelf.getAllBooks());
        assertEquals(1, bookShelf.getBookInfo(book).getTotal());

        Book copyOfBook = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(copyOfBook);

        assertEquals(Set.of(book), bookShelf.getAllBooks());
        assertEquals(2, bookShelf.getBookInfo(book).getTotal());
    }

    @Test
    void removeBook_RemoveBookWithNoCopies_ReturnsTrueOnBookRemoved() {
        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);

        assertTrue(bookShelf.removeBook(book));
        assertEquals(Set.of(), bookShelf.getAllBooks());
    }

    @Test
    void removeBook_RemoveBookWithCopies_ReturnsTrueOnBookRemoved() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        Book copyOfBook = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);
        bookShelf.addBook(copyOfBook);

        assertTrue(bookShelf.removeBook(book));
        assertEquals(Set.of(book), bookShelf.getAllBooks());
        assertEquals(1, bookShelf.getBookInfo(book).getTotal());
    }

    @Test
    void removeBook_ReturnFalseOnNoBookToRemove() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);

        assertFalse(bookShelf.removeBook(book));
    }

    @Test
    void removeAllBookInstances_ReturnsTrueOnBookRemoved() {

        Book book1 = new Book("n1", "a1", Category.PROGRAMMING);
        Book book2 = new Book("n2", "a2", Category.PROGRAMMING);
        Book copyOfBook2 = new Book("n2", "a2", Category.PROGRAMMING);
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        bookShelf.addBook(copyOfBook2);

        assertTrue(bookShelf.removeAllBookInstances(book1));
        assertEquals(Set.of(book2), bookShelf.getAllBooks());
        assertTrue(bookShelf.removeAllBookInstances(book2));
        assertEquals(Set.of(), bookShelf.getAllBooks());
    }

    @Test
    void removeAllBookInstances_ReturnsFalseOnNoBookToRemove() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);

        assertFalse(bookShelf.removeAllBookInstances(book));
    }

    @Test
    void lendBook_WorksCorrectlyWithNoCopies() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);

        bookShelf.lendBook(book);

        assertEquals(Set.of(book), bookShelf.getAllBooks());
        assertEquals(0, bookShelf.getBookInfo(book).getInStock());
    }

    @Test
    void lendBook_WorksCorrectlyWithCopies() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        Book copyOfBook = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);
        bookShelf.addBook(copyOfBook);

        bookShelf.lendBook(book);

        assertEquals(Set.of(book), bookShelf.getAllBooks());
        assertEquals(1, bookShelf.getBookInfo(book).getInStock());
    }

    @Test
    void lendBook_ThrowsWhenNoBooksInStock() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);

        bookShelf.lendBook(book);

        assertThrows(NoSuchElementException.class ,() -> bookShelf.lendBook(book));
    }

    @Test
    void lendBook_ThrowsOnBookDontExist() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);

        assertThrows(NoSuchElementException.class, () -> bookShelf.lendBook(book));
    }

    @Test
    void retrieveBook() {

        Book book = new Book("n1", "a1", Category.PROGRAMMING);
        bookShelf.addBook(book);

        bookShelf.lendBook(book);
        bookShelf.retrieveBook(book);

        assertEquals(Set.of(book), bookShelf.getAllBooks());
    }
}