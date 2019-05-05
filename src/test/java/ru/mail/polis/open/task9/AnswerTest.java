package ru.mail.polis.open.task9;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    @Test
    void test() {
        try {
            FileInputStream outputFileToTest = new FileInputStream(
                    "src\\main\\java\\ru\\mail\\polis\\open\\task9\\output.txt"
            );
            FileInputStream result = new FileInputStream(
                    "src\\main\\java\\ru\\mail\\polis\\open\\task9\\result.txt"
            );

            byte[] buffOutput = new byte[outputFileToTest.available()];
            outputFileToTest.read(buffOutput, 0, outputFileToTest.available());

            byte[] buffResult = new byte[result.available()];
            result.read(buffResult, 0, result.available());

            assertTrue(Arrays.equals(buffOutput, buffResult));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}