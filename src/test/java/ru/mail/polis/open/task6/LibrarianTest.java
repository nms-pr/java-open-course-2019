package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibrarianTest {

    private static Librarian librarian;
    private static Visitor visitor;
    private static Manager manager;
    private static Book firstBook;
    private static Book secondBook;
    private static Book notExistBook;

    @BeforeEach
    void beforeStart() {
        librarian = new Librarian("Василий", "Пупкин", 69);
        manager = new Manager("Геннадий", "Букин", 43);
        visitor = new Visitor("Евгений", "Музыченко", 22);
        manager.openTheLibrary();
    }

    @Test
    void giveBookByTitleTest() {
        firstBook = Library.getBookList().get(0);
        notExistBook = new Book(6, 33, "ЖУЖУЖУ", "БЗЗЗ");
        assertEquals(firstBook, librarian.giveBook(firstBook.bookTitle, visitor));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(null, visitor));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(notExistBook.getBookTitle(), visitor));
    }

    @Test
    void giveBookByIdTest() {
        firstBook = Library.getBookList().get(0);
        notExistBook = new Book(6, 33, "ЖУЖУЖУ", "БЗЗЗ");
        assertEquals(firstBook, librarian.giveBook(firstBook.id, visitor));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(null, visitor));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveBook(notExistBook.getId(), visitor));
    }

    @Test
    void putBookTest() {
        firstBook = Library.getBookList().get(0);
        librarian.putBook(librarian.giveBook(firstBook.bookTitle, visitor), visitor);
        assertEquals(firstBook, Library.getBookShelfList().get(firstBook.numberBookShelf).getBooksOnShelf()
            .get(firstBook.numberBookShelf));
        assertThrows(IllegalArgumentException.class, () -> librarian.putBook(null, visitor));
    }

    @Test
    void findBookTest() {
        firstBook = Library.getBookList().get(0);
        secondBook = Library.getBookList().get(1);
        assertEquals(firstBook, librarian.findBook(30160));
        assertEquals(secondBook, librarian.findBook("Мастер и Маргарита"));
        assertThrows(IllegalArgumentException.class, () -> librarian.findBook(1));
        assertThrows(IllegalArgumentException.class, () -> librarian.findBook("вовввооо"));
    }

    @AfterEach
    void end() {
        Library.setOpenOrClosed(false);
        Library.getVisitorList().clear();
        Library.getBusyBooks().clear();
        Library.getBookList().clear();
        Library.getBookShelfList().clear();
        Library.setCounter();
    }
}
