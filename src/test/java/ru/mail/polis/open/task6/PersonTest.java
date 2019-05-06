package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    Customer customer = new Customer("Иван", "Мужской", 15);
    Manager manager = new Manager("Василий", "Мужской", 36);
    Librarian librarian = new Librarian("Юлия", "Женский", 42);

    @Test
    void checkGets() {
        assertEquals("Иван", customer.getFullName());
        assertEquals("Мужской", customer.getSex());
        assertEquals(15, customer.getAge());

        assertEquals("Василий", manager.getFullName());
        assertEquals("Мужской", manager.getSex());
        assertEquals(36, manager.getAge());

        assertEquals("Юлия", librarian.getFullName());
        assertEquals("Женский", librarian.getSex());
        assertEquals(42, librarian.getAge());
    }
}
