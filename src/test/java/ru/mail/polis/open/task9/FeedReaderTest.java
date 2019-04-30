package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FeedReaderTest {

    @Test
    void nullParamsTest() {
        assertThrows(MalformedURLException.class, () -> new FeedReader(null, "output.txt"));
        assertThrows(NullPointerException.class, () -> new FeedReader("https://lenta.ru/rss/news", null));
    }

    @Test
    void workTest() {
        try {
            FeedReader fr = new FeedReader("https://lenta.ru/rss/news", "actual.txt");
            fr.readFeed();
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new File("input.xml")));
            FileWriter output = new FileWriter(new File("expected.txt"));
            for (SyndEntry entry : feed.getEntries()) {
                output.write(
                        entry.getTitle() + "\n"
                                + entry.getDescription().getValue() + "\n"
                                + entry.getLink() + "\n"
                                + entry.getPublishedDate() + "\n\n"
                );
            }
            assertEquals(
                    Files.readAllLines(Paths.get("actual.txt"))
                            .toString().replaceAll(System.lineSeparator(), "")
                            .replaceAll(" ", ""),
                    Files.readAllLines(Paths.get("expected.txt"))
                            .toString().replaceAll(System.lineSeparator(), "")
                            .replaceAll(" ", ""));
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
    }
}