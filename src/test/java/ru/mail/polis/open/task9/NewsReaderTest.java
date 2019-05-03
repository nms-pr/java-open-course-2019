package ru.mail.polis.open.task9;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class NewsReaderTest {
    private URL url;
    private NewsReader newsReader;
    private String fileName;

    @Test
    void read() throws IOException {
        url = new URL("https://news.yandex.ru/army.rss");
        fileName = "src/test/java/ru/mail/polis/open/task9/localRssContent";
        newsReader = new NewsReader(url, fileName);
        try {
            newsReader.read();
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        assertNotEquals(0, Path.of(fileName).toFile().length());
        Files.deleteIfExists(Path.of(fileName));
    }

    @Test
    void empryRead() throws IOException {
        url = new URL("https://news.rambler.ru/rss/head/?limit=100");
        fileName = "";

        assertThrows(FileNotFoundException.class, () -> new NewsReader(url, fileName));
    }
}

