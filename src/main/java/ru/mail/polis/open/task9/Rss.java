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
    public static void romeParse(String url, String output) {
        String separator = System.lineSeparator();
        try {
            URL feedSource = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            try (FileWriter writer = new FileWriter(output, false)) {
                for (SyndEntry entry : feed.getEntries()) {
                    writer.write(entry.getTitle());
                    writer.append(separator);
                    writer.write(entry.getDescription().getValue());
                    writer.append(separator);
                    writer.write(entry.getLink());
                    writer.append(separator);
                    writer.write(entry.getPublishedDate().toString());
                    writer.append(separator);
                }
            }
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
