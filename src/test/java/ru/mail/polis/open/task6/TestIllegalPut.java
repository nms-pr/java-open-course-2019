package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.narnia2;

public class TestIllegalPut {
    @Test
    void testIllegalPut() {
        assertThrows(IllegalCallerException.class, () -> ManagingPerson.store.put(narnia2, 12));
    }
}
