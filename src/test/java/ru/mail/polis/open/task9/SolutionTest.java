package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {
    @Test
    void test() {
        try {
            FileInputStream outputFileToTest = new FileInputStream(
                    "src\\main\\java\\ru\\mail\\polis\\open\\task9\\output.txt"
            );
            FileInputStream expected = new FileInputStream(
                    "src\\main\\java\\ru\\mail\\polis\\open\\task9\\expected.txt"
            );

            byte[] buffOutput = new byte[outputFileToTest.available()];
            outputFileToTest.read(buffOutput, 0, outputFileToTest.available());

            byte[] buffExpected = new byte[expected.available()];
            expected.read(buffExpected, 0, expected.available());

            assertTrue(Arrays.equals(buffOutput, buffExpected));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
