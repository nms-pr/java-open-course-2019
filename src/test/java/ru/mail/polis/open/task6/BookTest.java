package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BookTest {

    @Test
    void setAsIncomingTest() {
        Book book = new Book("Gorgorod", 5);
        Assertions.assertNull(book.getRecipient());
        Assertions.assertEquals(0, book.getIncomingDate());
        Assertions.assertEquals(0, book.getDateOfReturn());
        book.setAsIncoming();
        Assertions.assertNull(book.getRecipient());
        Assertions.assertEquals(0, book.getDateOfReturn());
        Assertions.assertTrue(new Date().getTime() - book.getIncomingDate() < 60 * 1000);
    }

    @Test
    void setAsGivenOutTest() {
        Visitor visitor = new Visitor("Мирон", "Янович");
        Book book = new Book("some name", 666);
        book.setAsGivenOut(visitor);
        Assertions.assertEquals(visitor, book.getRecipient());
        Assertions.assertTrue(new Date().getTime() - book.getDateOfGivingOut() < 60 * 1000);

    }
}
