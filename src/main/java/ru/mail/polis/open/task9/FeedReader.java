package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class FeedReader {

    SyndFeed feed;
    URL url;
    File file;

    public FeedReader(String link, String file) throws MalformedURLException {
        this.url = new URL(link);
        this.file = new File(file);
    }

    public void readFeed() {
        SyndFeedInput input = new SyndFeedInput();
        try {
            feed = input.build(new XmlReader(url));
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }
        try {
            FileWriter output = new FileWriter(file);
            for (SyndEntry entry : feed.getEntries()) {
                output.write(
                        entry.getTitle() + "\n"
                        + entry.getDescription().getValue() + "\n"
                        + entry.getLink() + "\n"
                        + entry.getPublishedDate() + "\n\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
