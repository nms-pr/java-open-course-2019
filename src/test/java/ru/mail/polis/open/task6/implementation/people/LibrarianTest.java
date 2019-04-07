package ru.mail.polis.open.task6.implementation.people;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.implementation.BookShelf;
import ru.mail.polis.open.task6.implementation.Library;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibrarianTest {

    private static Librarian librarian;
    private static Book book1;
    private static Book book2;
    private static Book book3;
    private static Book book4;
    private static Book book5;
    private static Book book6;

    @BeforeAll
    static void createLibrary() {
        BookShelf bookShelf = new BookShelf();
        librarian = new Librarian(new Person("name", "surname"));
        Library library = new Library(bookShelf, new Manager(new Person("name", "surname")), librarian);
        librarian.assignToLibrary(library);

        library.open();

        book1 = new Book("n1", "a1", Category.PROGRAMMING);
        book2 = new Book("n1", "a1", Category.PROGRAMMING);
        book3 = new Book("n2", "a2", Category.PROGRAMMING);
        book4 = new Book("n3", "a3", Category.PROGRAMMING);
        book5 = new Book("n4", "a4", Category.HISTORY);
        book6 = new Book("n4", "a2", Category.HISTORY);
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        bookShelf.addBook(book3);
        bookShelf.addBook(book4);
        bookShelf.addBook(book5);
        bookShelf.addBook(book6);
    }

    @Test
    void getAllBooks() {

        assertEquals(Set.of(book1, book3, book4, book5, book6), librarian.getAllBooks());
    }

    @Test
    void getBooksByCategory() {

        assertEquals(Set.of(book1, book3, book4), librarian.getBooksByCategory(Category.PROGRAMMING));
        assertEquals(Set.of(book5, book6), librarian.getBooksByCategory(Category.HISTORY));
        assertEquals(Set.of(), librarian.getBooksByCategory(Category.FICTION));
    }

    @Test
    void getBooksByAuthor() {

        assertEquals(Set.of(book4), librarian.getBooksByAuthor("a3"));
        assertEquals(Set.of(book1), librarian.getBooksByAuthor("a1"));
        assertEquals(Set.of(book3, book6), librarian.getBooksByAuthor("a2"));
    }
}