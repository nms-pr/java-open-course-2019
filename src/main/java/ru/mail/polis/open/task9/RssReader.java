package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import org.jetbrains.annotations.NotNull;

public class RssReader {
    private URL url;
    private String fileName;
    private SyndFeed syndFeed;

    public RssReader(@NotNull URL url, @NotNull String fileName) {
        if (url == null) {
            throw new IllegalArgumentException("URL can't be null");
        }
        if (fileName == null) {
            throw new IllegalArgumentException("File name can't be null");
        }
        this.url = url;
        this.fileName = fileName;
    }

    public void writeToFile() throws IOException, FeedException {
        this.syndFeed = new SyndFeedInput().build(new XmlReader(url));
        FileWriter writer = new FileWriter(fileName);
        for (SyndEntry currentEntry: syndFeed.getEntries()) {
            writer.write(
                    currentEntry.getTitle() + "\r\n"
                            + currentEntry.getDescription().getValue() + "\r\n"
                            + currentEntry.getLink() + "\r\n"
                            + currentEntry.getPublishedDate() + "\r\n"
                            + "\r\n"
            );
        }
        writer.close();
    }
}
