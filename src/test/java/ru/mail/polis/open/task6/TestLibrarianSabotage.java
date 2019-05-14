package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLibrarianSabotage {
    @Test
    void testCheatingLibrarian() {
        Manager paulManager = new Manager("Paul");
        Librarian jackLibrarian = new Librarian("Jack");
        LibraryClient dannyClient = new LibraryClient("Danny");
        paulManager.openLibrary();
        dannyClient.askLibrarian();
        assertThrows(IllegalCallerException.class, jackLibrarian::becomeFree);
    }
}
