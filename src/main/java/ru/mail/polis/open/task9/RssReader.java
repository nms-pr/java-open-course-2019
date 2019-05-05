package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class RssReader {

    private URL feedUrl;
    private String fileName;
    private final SyndFeedInput input = new SyndFeedInput();

    public RssReader(URL feedUrl, String fileName) {
        this.feedUrl = feedUrl;
        this.fileName = fileName;
    }

    public void readRss() throws IOException, FeedException {
        SyndFeed syndFeed = input.build(new XmlReader(feedUrl));

        try (FileWriter writer = new FileWriter(fileName)) {
            for (SyndEntry feed : syndFeed.getEntries()) {
                writer.write(feed.getTitle() + "\n"
                        + feed.getDescription().getValue() + "\n"
                        + feed.getLink() + "\n"
                        + feed.getPublishedDate() + "\n");
            }
        }
    }


}
