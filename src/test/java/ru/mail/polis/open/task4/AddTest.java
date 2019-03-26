package ru.mail.polis.open.task4;

import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task4.expressions.Add;
import ru.mail.polis.open.task4.expressions.Const;

import static org.junit.jupiter.api.Assertions.assertEquals;

//todo
class AddTest {

    @Test
    void test() {
        assertEquals(
            3,
            new Add(
                new Const(1),
                new Const(2)
            ).evaluate()
        );
    }

}