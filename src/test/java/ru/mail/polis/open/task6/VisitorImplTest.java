package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitorImplTest {
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
    void takeBook() {
        visitor.takeBook("Том и Джерри", library);
        assertEquals(true, visitor.takenBooks.contains(book));
    }

    @Test
    void returnBook() {
        takeBook();

        visitor.returnBook(book);
        assertEquals(false, visitor.takenBooks.contains(book));
    }
}