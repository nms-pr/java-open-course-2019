package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    protected static Book b1 = new Book(123, "Test", "A2", Book.TypeOfLiterature.Documentary);
    protected static Book b2 = new Book(123, "Test", "A2", Book.TypeOfLiterature.Documentary);
    protected static Book b3 = new Book(123, "Test1", "A2", Book.TypeOfLiterature.Documentary);
    protected static Book b4 = new Book(123, "Test1", "A2", Book.TypeOfLiterature.Memoirs);

    @Test
    void equalsTest() {
        assertEquals(b1, b2);

        Calendar issueTime = Calendar.getInstance();
        Calendar returnTime = Calendar.getInstance();
        Book.InformationWhoTook informationWhoTook = new Book.InformationWhoTook(issueTime, returnTime, VisitorTest.slava);

        b1.addInformationWhoTooks(informationWhoTook);
        assertNotEquals(b1, b2);

        b2.addInformationWhoTooks(informationWhoTook);
        assertEquals(b1, b2);

        b1.addInformationWhoTooks(new Book.InformationWhoTook(issueTime, returnTime, VisitorTest.kostya));
        assertNotEquals(b1, b2);

        assertNotEquals(b2, b3);
        assertNotEquals(b3, b4);
    }


}