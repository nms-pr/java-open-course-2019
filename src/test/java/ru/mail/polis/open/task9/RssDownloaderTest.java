package ru.mail.polis.open.task9;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RssDownloaderTest {
    private URL url;
    private RssDownloader downloader;
    private String fileName;

    @Test
    void download() throws IOException {
        url = new URL("https://news.rambler.ru/rss/head/?limit=100");
        fileName = "/home/mksnkv/IdeaProjects/java-open-course-2019/src/test/"
            + "java/ru/mail/polis/open/task9/localRssContent";
        downloader = new RssDownloader(url, fileName);
        try {
            downloader.download();
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        assertNotEquals(0, Path.of(fileName).toFile().length());
    }

    @Test
    void emptyDownload() throws IOException {
        url = new URL("https://news.rambler.ru/rss/head/?limit=100");
        fileName = "";
        downloader = new RssDownloader(url, fileName);
        assertThrows(FileNotFoundException.class, () -> downloader.download());
    }
}