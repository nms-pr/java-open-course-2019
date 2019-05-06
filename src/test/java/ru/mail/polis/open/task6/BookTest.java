package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookTest {
    private int id = 1;
    private String title = "Название 1";
    private int section = 1;
    private int shelfPlace = 1;
    Book book = new Book(id, title, section, shelfPlace);
    Customer customer = new Customer("Иван", "Мужской", 15);
    Calendar data = new GregorianCalendar(2001, 1,1);

    @Test
    void checkGets() {
        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(section, book.getSection());
        assertEquals(shelfPlace, book.getShelfPlace());
    }

    @Test
    void checkSets() {
        book.setCustomerInfo(customer);
        assertEquals(customer, book.getCustomerInfo());
        book.setAvailabilityTrue();
        assertTrue(book.isAvailable());
        book.setAvailabilityFalse();
        assertFalse(book.isAvailable());
        book.setDateOfReturn(data);
        assertEquals(data, book.getDateOfReturn());
        book.setDateOfGiving(data);
        assertEquals(data, book.getDateOfGiving());
    }
}
