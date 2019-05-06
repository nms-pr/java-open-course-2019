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
    private static final String ONE_BOOK_S = "С.В.Мильке, \"Научная наука\", Жанр: Scientific, ID: 1, В наличии: 15";
    private static final String TWO_BOOK_S = "Д.П.Оперечный, \"Стендапы\", Жанр: Fantastic, ID: 6, В наличии: 15";


    @BeforeAll
    static void getManager() {
        library = new Library(30, 30);
        manager = library.getManager();
    }

    @Test
    void addNewBooks() {
        Librarian librarian = library.getLibrarian();
        manager.addNewBooks(
                new BookCard("Научная наука", "С.В.Мильке", Section.Scientific, 15, 1),
                new BookCard("Книга про регби", "С.П.Ортсмен", Section.Poetry, 20,2),
                new BookCard("Книга про камни", "С.В.Мильке", Section.Classical, 30,3),
                new BookCard("Книга про брик", "А.В.Рыбин", Section.Educational, 12,4),
                new BookCard("Рецепты куличей", "Х.В.Пасхальный", Section.Adventures, 10, 5),
                new BookCard("Стендапы", "Д.П.Оперечный", Section.Fantastic, 15, 6),
                new BookCard("В устах хех, на душе кек", "Е.В.Петросян", Section.Other, 18, 7)
        );
        assertEquals(librarian.getAvailableBooks()[0], ONE_BOOK_S);
        assertEquals(librarian.getAvailableBooks()[5], TWO_BOOK_S);
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
