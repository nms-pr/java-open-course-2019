package ru.mail.polis.open.task9;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.rometools.rome.io.FeedException;

class RssToFileSaverTest {
    static RssToFileSaver rssToFileSaver;

    @BeforeAll
    static void init() {
        String lineSeparator = "-------";
        rssToFileSaver = new RssToFileSaver(lineSeparator, "dd-MM-yyyy HH:mm:ss", "UTF-8");
    }

    @Test
    void saveToFileTest() {
        try {
            Path basePath =
                    Paths.get("src", "test", "java", "ru", "mail", "polis", "open", "task9");

            Path inputFilePath = basePath.resolve("test_input");

            URL inputFileUrl = inputFilePath.toUri().toURL();

            Path expectedFilePath = basePath.resolve("test_expected_result");
            Path resultFilePath = basePath.resolve("test_real_result");

            rssToFileSaver.saveToFile(inputFileUrl, resultFilePath.toAbsolutePath().toString());

            try (InputStream expected = new FileInputStream(expectedFilePath.toFile());
                    InputStream real = new FileInputStream(resultFilePath.toFile())) {

                if (!resultFilePath.toFile().exists() || !expectedFilePath.toFile().exists()) {
                    fail("файл не существует");
                }

                String s1 = new String(expected.readAllBytes(), "UTF-8");
                String s2 = new String(real.readAllBytes(), "UTF-8");

                if (s1.length() < 10 || s2.length() < 10) {
                    fail("что-то не так со строками");
                }

                assertTrue(s1.equals(s2));
            }
        } catch (IOException | FeedException e) {
            fail(e);
        }
    }

}
