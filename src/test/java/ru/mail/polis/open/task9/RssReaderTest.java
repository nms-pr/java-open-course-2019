package ru.mail.polis.open.task9;

import com.rometools.rome.io.FeedException;
import org.junit.jupiter.api.Test;
import java.net.URL;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RssReaderTest {
    private URL url;
    private String fileName;
    private RssReader rssReader;

    @Test
    void writeRssToFileTest() throws IOException, FeedException {
        url = new URL("https://news.yandex.ru/daily.rss");
        fileName = "src/test/java/ru/mail/polis/open/task9/test.txt";
        rssReader = new RssReader(url, fileName);
        rssReader.writeToFile();
        assertNotEquals(0, Path.of(fileName).toFile().length());
    }

    @Test
    void nullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            rssReader = new RssReader(null, fileName);
            assertNotEquals(0, Path.of(fileName).toFile().length());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            rssReader = new RssReader(url, null);;
        });
    }
}
