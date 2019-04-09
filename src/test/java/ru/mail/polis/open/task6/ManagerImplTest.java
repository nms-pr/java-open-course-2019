package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerImplTest {
    Library library;
    Library.LibrarianImpl librarian;
    Library.ManagerImpl manager;
    VisitorImpl visitor;
    Book book;

    @BeforeEach
    void beforeEachMethod() {
        library = new Library();
        library.setLibrarian(library.new LibrarianImpl(
                "Васильева",
                "Ольга",
                "Юрьевна",
                "ул. Пушкина 12",
                "+79817284501",
                40,
                'Ж'));
        library.setManager(library.new ManagerImpl(
                "Пупкин",
                "Василий",
                "Васильевич",
                "ул. Колотушкина 13",
                "+79817284213",
                34,
                'М'));
        manager = library.getManager();
        librarian = library.getLibrarian();
        visitor = new VisitorImpl(
                "Фёдоров",
                "Иван",
                "Александрович",
                "ул. Шестая",
                "+79817281212",
                37,
                'М');
        book = new Book(1, "Том и Джерри", 5, 5, library);
        manager.addBook(book);
    }

    @Test
    void delBook() {
        assertEquals(book, librarian.takeBook("Том и Джерри", visitor)); // Проверяем ищется ли такая книга вообще

        manager.delBook("Том и Джерри"); // Удаление по названию
        assertThrows(IllegalArgumentException.class, () -> librarian.takeBook("Том и Джерри", visitor));

        manager.addBook(book); // По новой добавляем книгу
        manager.delBook(book); // Удаление с передачей самой книги
        assertThrows(IllegalArgumentException.class, () -> librarian.takeBook("Том и Джерри", visitor));
    }

    @Test
    void addBook() {
        assertEquals(book, librarian.takeBook("Том и Джерри", visitor));
    }

    @Test
    void openLibrary() {
        manager.openLibrary();
        assertEquals(true, library.isOpened());
    }

    @Test
    void closeLibrary() {
        manager.closeLibrary();
        assertEquals(false, library.isOpened());
    }
}
