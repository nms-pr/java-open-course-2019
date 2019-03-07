package ru.mail.polis.open.task1.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzGameStrategyTest {

    private static FizzBuzzGameStrategy strategy;

    @BeforeAll
    static void initialize(){
        strategy = new FizzBuzzGameStrategy();
    }

    @Test
    void getCorrespondingString_IsInValidRange() {

        assertDoesNotThrow(() -> strategy.getCorrespondingString(0));
        assertDoesNotThrow(() -> strategy.getCorrespondingString(50));
        assertDoesNotThrow(() -> strategy.getCorrespondingString(100));
    }


    @Test
    void getCorrespondingString_NotInValidRange() {

        assertThrows(IllegalArgumentException.class, () -> strategy.getCorrespondingString(-1));
        assertThrows(IllegalArgumentException.class, () -> strategy.getCorrespondingString(101));
    }

    @Test
    void getCorrespondingString_DividedBy3() {

        assertEquals("Fizz", strategy.getCorrespondingString(3));
        assertEquals("Fizz", strategy.getCorrespondingString(6));
        assertEquals("Fizz", strategy.getCorrespondingString(9));
    }

    @Test
    void getCorrespondingString_DividedBy5() {

        assertEquals("Buzz", strategy.getCorrespondingString(5));
        assertEquals("Buzz", strategy.getCorrespondingString(10));
        assertEquals("Buzz", strategy.getCorrespondingString(20));
    }


    @Test
    void getCorrespondingString_DividedBy15() {

        assertEquals("FizzBuzz", strategy.getCorrespondingString(15));
        assertEquals("FizzBuzz", strategy.getCorrespondingString(30));
        assertEquals("FizzBuzz", strategy.getCorrespondingString(45));
    }


    @Test
    void getCorrespondingString_notDividend() {

        assertEquals("7", strategy.getCorrespondingString(7));
        assertEquals("13", strategy.getCorrespondingString(13));
        assertEquals("22", strategy.getCorrespondingString(22));
    }
}