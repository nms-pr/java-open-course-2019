package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.annaLibrarian;
import static ru.mail.polis.open.task6.UtilTest.physics;

public class TestRemoveOneBook {
    @Test
    void testRemoveOneBook() {
        assertThrows(IllegalCallerException.class, () -> annaLibrarian.removeOneBookFromCollection(physics,
                ManagingPerson.store));
    }
}
