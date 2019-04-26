package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class RssDataProvider {

    SyndFeed getNewsFeed(String url) throws IOException, FeedException {
        return new SyndFeedInput().build(new XmlReader(new URL(url)));
    }


    void writeNewsFeedToFile(String fileName, SyndFeed newsFeed) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        for (SyndEntry entry: newsFeed.getEntries()) {
            writer.write("Title: " +
                    entry.getTitle() +
                    "\nDescription: " +
                    entry.getDescription().getValue() +
                    "\nLink: " +
                    entry.getLink() +
                    "\nPublishing date: " +
                    entry.getPublishedDate() +
                    "\n-------------------\n");
        }

        writer.close();
    }

}
