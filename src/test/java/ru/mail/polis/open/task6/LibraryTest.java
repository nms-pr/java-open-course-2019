package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTest {
    Library library;
    Library.LibrarianImpl librarian;
    Library.ManagerImpl manager;

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
    }

    @Test
    void isOpened() {
        manager.openLibrary();
        assertEquals(true, library.isOpened());

        manager.closeLibrary();
        assertEquals(false, library.isOpened());
    }

    @Test
    void getManager() {
        assertEquals(manager, library.getManager());
    }

    @Test
    void setManager() {
        library.setManager(manager);
        assertEquals(manager, library.getManager());
    }

    @Test
    void getLibrarian() {
        assertEquals(librarian, library.getLibrarian());
    }

    @Test
    void setLibrarian() {
        library.setLibrarian(librarian);
        assertEquals(librarian, library.getLibrarian());
    }
}