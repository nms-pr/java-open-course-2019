package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    private Manager jackManager = new Manager("Jack");
    private Manager paulManager = new Manager("Paul");
    private Librarian jackLibrarian = new Librarian("Jack");
    private Librarian annaLibrarian = new Librarian("Anna");
    private Librarian peterLibrarian = new Librarian("Peter");
    private LibraryClient peterClient = new LibraryClient("Peter");
    private LibraryClient samClient = new LibraryClient("Sam");
    private LibraryClient dannyClient = new LibraryClient("Danny");
    private Manager.Book winnie = new Manager.Book(1,
            "Winnie the Pooh",
            new ArrayList<>(Arrays.asList("Fairytale", "Children", "Alan Milne")));
    private Manager.Book narnia1 = new Manager.Book(2,
            "Chronicles of Narnia",
            new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 1")));
    private Manager.Book myths = new Manager.Book(3,
            "Myths of Ancient Greece",
            new ArrayList<>(Arrays.asList("Mythology", "Education")));
    private Manager.Book physics = new Manager.Book(4,
            "Quantum Physics for stupid people",
            new ArrayList<>(Arrays.asList("Science", "Education")));
    private Manager.Book narnia2 = new Manager.Book(5,
            "Chronicles of Narnia",
            new ArrayList<>(Arrays.asList("Fairytale", "Family", "Children", "Mythology", "Part 2")));

    @Test
    void testLibraryOpenClosed() {
        assertThrows(IllegalStateException.class, () -> samClient.askLibrarian());
        jackManager.openLibrary();
        assertDoesNotThrow(() -> samClient.askLibrarian());
        paulManager.closeLibrary();
        assertThrows(IllegalStateException.class, () -> dannyClient.askLibrarian());
    }
}
