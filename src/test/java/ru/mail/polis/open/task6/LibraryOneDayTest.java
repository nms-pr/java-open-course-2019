package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LibraryOneDayTest {
    private static Book book = new Book(1, 2, "Карта дней", "Фантастика");
    private static Book book1 = new Book(5, 6, "Пена", "Фантастика");
    private static Book book2 = new Book(7, 2, "Кукушка", "Сказки");
    private static CustomerImpl customer =
        new CustomerImpl("Сергей", "Кравченко", "Петрович", (byte) 30);

    @BeforeAll
    static void setPositions() {
        Library.manager.openLibrary();
        assertEquals(true, Library.getStatus());
    }

    @Test
    void work() {
        assertEquals(9, Library.getBooks().size());
        Library.librarian.giveBook("The Da Vinci Code", customer);
        assertEquals(true, Library.containCustomer(customer));

        assertEquals(8, Library.getBooks().size());

        assertEquals(1, Library.getBusyBooks().size());

        assertThrows(IllegalArgumentException.class,
            () -> Library.librarian.giveBook("The Da Vinci Code", Library.getCustomers().get(2)));

        Library.manager.add(book1);

        assertEquals(9, Library.getBooks().size());

        Library.librarian.putBook(customer.takenBooks.get(0), customer);

        assertEquals(10, Library.getBooks().size());

        assertEquals(0, Library.getBusyBooks().size());

        assertThrows(IllegalArgumentException.class,
            () -> Library.librarian.findBook("Кукушка"));

        Library.manager.add(book2);

        assertEquals(book2, Library.librarian.findBook("Кукушка"));

        Library.manager.closeLibrary();
        assertThrows(IllegalArgumentException.class,
            () -> Library.librarian.giveBook("Виноваты звёзды", Library.getCustomers().get(2)));

    }

    @AfterAll
    static void clean() {
        Library.setStatus(false);
        Library.setCounter();
        Library.getCustomers().clear();
        Library.getBusyBooks().clear();
        Library.getShelfsInLibrary().clear();
        Library.getBooks().clear();
    }

}