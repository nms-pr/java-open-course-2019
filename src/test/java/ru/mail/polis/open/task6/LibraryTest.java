package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LibraryTest {
    private static LibrarianImpl librarian;
    private static CustomerImpl customer;

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
                "Иван",
                "Иванов",
                "Иванович",
                (byte) 22
        );
        Library.work();

    }

    @Test
    void checkOpening() {
        Library.manager.openTheLibrary();
        assertTrue(Library.getStatus());

    }

    @Test
    void checkCustomers() {
        Library.newCustomerCome(new CustomerImpl("Илья", "Денисов", "Денисович", (byte) 40));
        assertEquals(10,
                Library.getCustomers().size());


    }



}