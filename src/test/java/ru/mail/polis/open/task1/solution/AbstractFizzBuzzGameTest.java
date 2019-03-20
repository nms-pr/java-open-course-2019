package ru.mail.polis.open.task1.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author mikhail.nechaev
 * Since 04/03/2019
 */
class AbstractFizzBuzzGameTest {

    @Test
    void fromAndToForPrintShouldBeInRangeFrom0To100() {
        assertDoesNotThrow(() -> AbstractFizzBuzzGame.checkArgs(0, 100));
        assertThrows(
            IllegalArgumentException.class,
            () -> AbstractFizzBuzzGame.checkArgs(-1, 100)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> AbstractFizzBuzzGame.checkArgs(0, 101)
        );
    }

    @Test
    void fromShouldBeLessOrEqualThanTo() {
        assertDoesNotThrow(() -> AbstractFizzBuzzGame.checkArgs(0, 0));
        assertThrows(
            IllegalArgumentException.class,
            () -> AbstractFizzBuzzGame.checkArgs(2, 1)
        );
    }

    @Test
    void numberForGeneratedWordShouldBeInRangeFrom0To100() {
        assertDoesNotThrow(() -> AbstractFizzBuzzGame.generateWord(0));
        assertThrows(
            IllegalArgumentException.class,
            () -> AbstractFizzBuzzGame.generateWord(-1)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> AbstractFizzBuzzGame.generateWord(101)
        );

    }

    @Test
    void multiplesOf3PrintFizz() {
        assertEquals("Fizz", AbstractFizzBuzzGame.generateWord(3));
        assertEquals("Fizz", AbstractFizzBuzzGame.generateWord(6));
        assertEquals("Fizz", AbstractFizzBuzzGame.generateWord(9));
        assertEquals("Fizz", AbstractFizzBuzzGame.generateWord(12));
        assertTrue(AbstractFizzBuzzGame.generateWord(15).startsWith("Fizz"));
    }

    @Test
    void multiplesOf5PrintFizz() {
        assertEquals("Buzz", AbstractFizzBuzzGame.generateWord(5));
        assertEquals("Buzz", AbstractFizzBuzzGame.generateWord(10));
        assertTrue(AbstractFizzBuzzGame.generateWord(15).endsWith("Buzz"));
    }

    @Test
    void multiplesOf15PrintFizzBuzz() {
        assertEquals("FizzBuzz", AbstractFizzBuzzGame.generateWord(15));
    }

    @Test
    void testNumber() {
        assertEquals("1", AbstractFizzBuzzGame.generateWord(1));
        assertEquals("2", AbstractFizzBuzzGame.generateWord(2));
        assertEquals("4", AbstractFizzBuzzGame.generateWord(4));
        assertEquals("7", AbstractFizzBuzzGame.generateWord(7));
        assertEquals("8", AbstractFizzBuzzGame.generateWord(8));
    }

}