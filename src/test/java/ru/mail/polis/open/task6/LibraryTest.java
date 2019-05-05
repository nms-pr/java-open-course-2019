package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LibraryTest {

    static LibrarianImpl librarian;

    @AfterAll
    static void end() {
        Library.setStatus(false);
        Library.setCounter();
        Library.getCustomers().clear();
        Library.getBusyBooks().clear();
        Library.getShelfsInLibrary().clear();
        Library.getBooks().clear();
    }

    @BeforeAll
    static void beforeStart() {
        librarian = new LibrarianImpl(
                "David",
                "Smith",
                "Peter",
                (byte) 22
        );
        Library.work();

    }

    @Test
    void checkOpening() {
        Library.manager.openLibrary();
        assertTrue(Library.getStatus());

    }

    @Test
    void checkCustomers() {
        Library.newCustomerCome(new CustomerImpl("Natasha", "Romanov", "Ivan", (byte) 40));
        assertEquals(10,
                Library.getCustomers().size());


    }



}