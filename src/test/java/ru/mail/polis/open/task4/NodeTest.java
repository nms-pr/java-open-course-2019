package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    @Test
    void testWorkingEquals() {
        Node node1 = new Node(
            new Const(4),
            null
        );

        Node node2 = new Node(
            new Const(4),
            null
        );
        assertEquals(node1, node2);
    }
}
