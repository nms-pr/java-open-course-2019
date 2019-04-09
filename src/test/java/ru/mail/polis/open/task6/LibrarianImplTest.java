package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibrarianImplTest {
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
        assertEquals(book, librarian.takeBook("Том и Джерри", visitor));
        assertThrows(IllegalArgumentException.class, () ->
                librarian.takeBook("Том и Джерри", visitor)); // Такой книги уже нет, кинет исключение
    }

    @Test
    void putBook() {
        visitor.takeBook("Том и Джерри", library);
        assertThrows(IllegalArgumentException.class, () ->
                librarian.takeBook("Том и Джерри", visitor)); // Такой книги уже нет, кинет исключение

        visitor.returnBook(visitor.takenBooks.get(0)); // Вовзвращаем книгу
        assertEquals(book,
                librarian.takeBook("Том и Джерри", visitor)); // Раз книгу вернули, то получится снова её взять
    }

    @Test
    void getAvailableBooks() {
        HashMap<String, ArrayList<Book>> avaiableBooks = librarian.getAvailableBooks();
        assertEquals(true, avaiableBooks.containsKey("Том и Джерри"));

        librarian.takeBook("Том и Джерри", visitor); // Книгу забрали
        avaiableBooks = librarian.getAvailableBooks();
        assertEquals(false, avaiableBooks.containsKey("Том и Джерри"));
    }

    @Test
    void getHistory() {
        visitor.takeBook("Том и Джерри", library);
        HashMap<Visitor, ArrayList<Book>> history = librarian.getHistory();
        ArrayList<Book> historyOfPerson = history.get(visitor);
        assertEquals(book, historyOfPerson.get(0));
    }
}
