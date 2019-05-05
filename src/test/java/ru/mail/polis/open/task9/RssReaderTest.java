package ru.mail.polis.open.task9;

import com.rometools.rome.io.FeedException;
import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;


class RssReaderTest {

    private URL feedUrl;
    private String fileName;
    private final String actualFile =
            "D:\\IdeaProjects\\java-open-course-2019\\src\\main\\java\\ru\\mail\\polis\\open\\task9\\ActualFile.txt";

    @Test
    void readTest() throws IOException, FeedException {
        feedUrl = new URL("https://news.yandex.ru/politics.rss");
        fileName = actualFile;
        try {
            new RssReader(feedUrl, fileName).readRss();
        } catch (MalformedInputException e) {
            e.printStackTrace();
        }
        assertNotEquals(0, Path.of(actualFile).toFile().length());
    }

}