package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkOfAllTest {
    private Book book1 = new Book(1, "Название 1", 1, 1);
    private Book book2 = new Book(2, "Название 2", 2, 2);
    private Customer customer = new Customer("Иван", "Мужской", 15);
    private Manager manager = new Manager("Василий", "Мужской", 36);
    private Librarian librarian = new Librarian("Юлия", "Женский", 42);
    private Library library = new Library(manager, librarian, 100);

    @Test
    void tests() {
        manager.openLibrary(library);
        manager.addBook(library,1, "Название 1", 1, 1);
        manager.addBook(library,2, "Название 2", 2, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            manager.addBook(library, 5, "5", 5, 101);
        });

        customer.getBook(library, book1);
        assertEquals(1, customer.whatOnHands().size());

        customer.returnBook(library, book1);
        assertEquals(0, customer.whatOnHands().size());

        assertThrows(IllegalArgumentException.class, () -> {
            customer.returnBook(library, book1);
        });

        manager.closeLibrary(library);
        assertThrows(IllegalArgumentException.class, () -> {
            customer.getBook(library, book2);
        });

        manager.openLibrary(library);
        librarian.giveBook(library,customer, book1);
        assertEquals(1, customer.whatOnHands().size());
    }
}
