package ru.mail.polis.open.invertedindex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    private File testFile;
    private static final String TEST_FILE_NAME = "testFile";

    @BeforeEach
    void createTestHtmlFile() {
        testFile = new File(TEST_FILE_NAME);
        try {
            if (!testFile.createNewFile()) {
                System.out.println("Error creating test file.");
            }
        } catch (IOException e) {
            fail("Error creating test file.");
        }

        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "</head\n"
                    + "<body>\n"
                    + "</body>"
                    + "</html>");

            writer.flush();
        } catch (IOException e) {
            fail("Error filling test file.");
        }
    }

    @Test
    void test2() {
        new Parser("file:" + TEST_FILE_NAME, 1, 1, new CopyOnWriteArrayList<>()).call();
        assertTrue(ParsersManager.getFutures().isEmpty());
    }

    @AfterEach
    void deleteTestFile() {
        if (!testFile.delete()) {
            fail("Error deleting test file.");
        }
    }

}
