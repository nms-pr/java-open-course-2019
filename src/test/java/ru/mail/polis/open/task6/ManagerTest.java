package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    private static Library library;
    private static Manager manager;

    private static final String CLOSED_TEXT = "Библиотека закрыта";
    private static final String OPENED_TEXT = "Библиотека открыта";
    private static final String ONE_BOOK_S = "С.В.Мильке, \"Научная наука\", Жанр: Scientific, ID: 2, В наличии: 15";


    @BeforeAll
    static void getManager() {
        library = new Library(30, 30);
        manager = library.getManager();
    }

    @Test
    void addNewBooks() {
        Librarian librarian = library.getLibrarian();
        manager.addNewBooks(
                new BookCard("Научная наука", "С.В.Мильке", Section.Scientific, 15)
        );
        assertEquals(librarian.getAvailableBooks()[0], ONE_BOOK_S);
    }

    @Test
    void closeLibrary() {
        manager.closeLibrary();
        assertEquals(library.getStatus(), CLOSED_TEXT);
    }

    @Test
    void openLibrary() {
        manager.openLibrary();
        assertEquals(library.getStatus(), OPENED_TEXT);
    }

    @Test
    void deleteBooks() {
        manager.deleteBooks(1);
    }

    @Test
    void getDepository() {

    }
}
