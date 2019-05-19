package ru.mail.polis.open.invertedIndex;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class OutputManagerTest {


    private static PrintStream defaultSystemOut;
    private static ByteArrayOutputStream testBuffer;
    private static Set<String> outputInformation;
    private static final String OUTPUT_FILE_NAME = "testOutputFile";



    @BeforeAll
    static void createTestInformation() {
        outputInformation = new LinkedHashSet<>();
        outputInformation.add("123");
        outputInformation.add("abc");
        outputInformation.add("45rt");
    }


    @BeforeAll
    static void overrideSystemOut() {
        defaultSystemOut = System.out;
        testBuffer = new ByteArrayOutputStream();
        System.setOut(
                new PrintStream(
                        new BufferedOutputStream(
                                testBuffer
                        )
                )
        );
    }

    @Test
    void writeToConsoleTest() {

        OutputManager.write(outputInformation, null, "request");

        assertEquals(
                "Result:\n" + "\t123\n" + "\tabc\n" + "\t45rt\n",
                testBuffer.toString());
    }



    @Test
    void writeToFileTest() {
        String request = "request";

        OutputManager.write(outputInformation, OUTPUT_FILE_NAME, request);


        try {
            String actualString = makeStringFromFileLines(Files.readAllLines(Paths.get(OUTPUT_FILE_NAME)));
            String expectedString = "Request:\n\t" + request + "\nResult:" + "\n\t123" + "\n\tabc" + "\n\t45rt";

            assertEquals(expectedString, actualString);
        } catch (Exception ignore){
            fail();
        }
        finally {
            deleteTestFile();
        }

    }

    private String makeStringFromFileLines(List<String> lines) {
        StringJoiner joiner = new StringJoiner("\n");

        for (String line:lines) {
            joiner.add(line);
        }

        return joiner.toString();
    }

    private void deleteTestFile() {
        new File(OUTPUT_FILE_NAME).delete();
    }


    @AfterEach
    void resetBuffer() {
        testBuffer = new ByteArrayOutputStream();
        overrideSystemOut();
    }

    @AfterAll
    static void resetOut() {
        System.setOut(defaultSystemOut);
    }

}
