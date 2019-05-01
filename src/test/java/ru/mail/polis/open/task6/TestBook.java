package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mail.polis.open.task6.Person.LINE_SEPARATOR;
import static ru.mail.polis.open.task6.UtilTest.physics;
import static ru.mail.polis.open.task6.UtilTest.narnia1;
import static ru.mail.polis.open.task6.UtilTest.winnie2;
import static ru.mail.polis.open.task6.UtilTest.winnie;
import static ru.mail.polis.open.task6.UtilTest.myths;

public class TestBook {
    @Test
    void testBook() {
        assertEquals("Chronicles of Narnia", narnia1.getName());
        assertEquals(4, physics.getId());
        assertEquals("[Mythology, Education]", Arrays.toString(myths.getPartitions().toArray()));
        assertEquals("Id: 1; Name: Winnie the Pooh"
                + LINE_SEPARATOR
                + "Partitions: Fairytale; Children; Alan Milne", winnie.toString());
        assertEquals(winnie, winnie2);
    }
}
