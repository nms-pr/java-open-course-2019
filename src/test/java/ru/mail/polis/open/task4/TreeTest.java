package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {

    @Test
    void testWorkingEquals() {
        Tree tree1 = new Tree(
            new Add(
                new Const(5),
                new Const(6)
            )
        );
        tree1.add(new Const(7));
        tree1.add(new Mult(
            new Const(6),
            new Const(7)
        ));

        Tree tree2 = new Tree(
            new Add(
                new Const(5),
                new Const(6)
            )
        );
        tree2.add(new Const(7));
        tree2.add(new Mult(
            new Const(6),
            new Const(7)
        ));

        assertEquals(tree1, tree2);
    }
}
