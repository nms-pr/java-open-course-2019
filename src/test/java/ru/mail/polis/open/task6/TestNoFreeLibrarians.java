package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestNoFreeLibrarians {
    @Test
    void testLibrarians() {
        Librarian lib1 = new Librarian("John");
        Librarian lib2 = new Librarian("Helen");
        Librarian lib3 = new Librarian("Samantha");
        Manager paulManager = new Manager("Paul");
        LibraryClient peterClient = new LibraryClient("Peter");
        LibraryClient dannyClient = new LibraryClient("Danny");
        paulManager.openLibrary();
        assertNotNull(peterClient.askLibrarian());
        assertNotNull(dannyClient.askLibrarian());
        LibraryClient samClient = new LibraryClient("Sam");
        assertNotNull(samClient.askLibrarian());
        LibraryClient maxClient = new LibraryClient("Max");
        assertNull(maxClient.askLibrarian());
    }
}
