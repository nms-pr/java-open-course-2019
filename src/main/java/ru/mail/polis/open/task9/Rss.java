package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class Rss {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        String separator = System.lineSeparator();
        try {
            URL feedSource = new URL(args[0]);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            try (FileWriter writer = new FileWriter(args[1], false)) {
                for (SyndEntry entry : feed.getEntries()) {
                    writer.write(entry.getTitle() == null ? "null" : entry.getTitle());
                    writer.append(separator);
                    writer.write(entry.getDescription().getValue() == null ? "null"
                            : entry.getDescription().getValue());
                    writer.append(separator);
                    writer.write(entry.getLink() == null ? "null" : entry.getLink());
                    writer.append(separator);
                    writer.write(entry.getPublishedDate().toString() == null ? "null"
                            : entry.getPublishedDate().toString());
                    writer.append(separator);
                }
            }
        } catch (FeedException e) {
            System.out.println("Feed Exception");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
