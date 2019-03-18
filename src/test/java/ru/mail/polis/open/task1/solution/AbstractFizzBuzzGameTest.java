package ru.mail.polis.open.task1.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
class AbstractFizzBuzzGameTest {

    private static AbstractFizzBuzzGame stubFizzBuzzGame = new AbstractFizzBuzzGame() {
        @Override
        void print(String word) {
            throw new UnsupportedOperationException("todo: implement this");
        }

        @Override
        void flush() {
            throw new UnsupportedOperationException("todo: implement this");
        }
    };

    @Test
    void fromAndToForPrintShouldBeInRangeFrom0To100() {
        try {
            stubFizzBuzzGame.checkArgs(0, 100);
        } catch (Throwable e) {
            fail(e);
        }
        assertThrows(
            IllegalArgumentException.class,
            () -> stubFizzBuzzGame.checkArgs(-1, 100)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> stubFizzBuzzGame.checkArgs(0, 101)
        );
    }

    @Test
    void fromShouldBeLessOrEqualThanTo() {
        try {
            stubFizzBuzzGame.checkArgs(0, 0);
        } catch (Throwable e) {
            fail(e);
        }
        assertThrows(
            IllegalArgumentException.class,
            () -> stubFizzBuzzGame.checkArgs(2, 1)
        );
    }

    @Test
    void numberForGeneratedWordShouldBeInRangeFrom0To100() {
        try {
            stubFizzBuzzGame.generateWord(0);
        } catch (Throwable e) {
            fail(e);
        }
        assertThrows(
            IllegalArgumentException.class,
            () -> stubFizzBuzzGame.generateWord(-1)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> stubFizzBuzzGame.generateWord(101)
        );

    }

    @Test
    void multiplesOf3PrintFizz() {
        assertEquals("Fizz", stubFizzBuzzGame.generateWord(3));
        assertEquals("Fizz", stubFizzBuzzGame.generateWord(6));
        assertEquals("Fizz", stubFizzBuzzGame.generateWord(9));
        assertEquals("Fizz", stubFizzBuzzGame.generateWord(12));
        assertTrue(stubFizzBuzzGame.generateWord(15).startsWith("Fizz"));
    }

    @Test
    void multiplesOf5PrintFizz() {
        assertEquals("Buzz", stubFizzBuzzGame.generateWord(5));
        assertEquals("Buzz", stubFizzBuzzGame.generateWord(10));
        assertTrue(stubFizzBuzzGame.generateWord(15).endsWith("Buzz"));
    }

    @Test
    void multiplesOf15PrintFizzBuzz() {
        assertEquals("FizzBuzz", stubFizzBuzzGame.generateWord(15));
    }

    @Test
    void testNumber() {
        assertEquals("1", stubFizzBuzzGame.generateWord(1));
        assertEquals("2", stubFizzBuzzGame.generateWord(2));
        assertEquals("4", stubFizzBuzzGame.generateWord(4));
        assertEquals("7", stubFizzBuzzGame.generateWord(7));
        assertEquals("8", stubFizzBuzzGame.generateWord(8));
    }

}