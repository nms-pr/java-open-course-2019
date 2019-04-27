package ru.mail.polis.open.task9;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RssToFileSaverTest {
    static RssToFileSaver rssToFileSaver;

    @BeforeAll
    static void init() {
        String lineSeparator = System.getProperty("line.separator");
        rssToFileSaver = new RssToFileSaver(lineSeparator, "dd-MM-yyyy HH:mm:ss");
    }

    @Test
    void saveToFileTest() {
        try {

            URL inputFileUrl = getClass().getResource("test_input");

            String expectedFilePath = getClass().getResource("test_expected_result").getPath();
            String resultFilePath = getClass().getResource("test_real_result").getPath();

            assertAll(() -> rssToFileSaver.saveToFile(inputFileUrl, resultFilePath));

            try (InputStream expected = new FileInputStream(new File(expectedFilePath));
                    InputStream real = new FileInputStream(new File(resultFilePath))) {
                assertTrue(Arrays.equals(expected.readAllBytes(), real.readAllBytes()));
            }
        } catch (IOException e) {
            fail(e);
        }
    }

}
