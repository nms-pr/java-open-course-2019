package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.mail.polis.open.task6.UtilTest.winnie;
import static ru.mail.polis.open.task6.UtilTest.narnia1;

public class TestManagerAddBook {
    @Test
    void testManagerAddBook() {
        Manager jackManager = new Manager("Jack");
        Manager paulManager = new Manager("Paul");
        paulManager.addNewBookToStore(winnie, 11);
        assertTrue(ManagingPerson.store.containsKey(winnie) && ManagingPerson.store.get(winnie) == 11);
        jackManager.addNewBookToStore(narnia1);
        assertTrue(ManagingPerson.store.containsKey(narnia1) && ManagingPerson.store.get(narnia1) == 1);
        paulManager.addNewBookToStore(narnia1, 4);
        assertTrue(ManagingPerson.store.containsKey(narnia1) && ManagingPerson.store.get(narnia1) == 5);
    }
}
