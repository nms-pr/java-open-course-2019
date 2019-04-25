package ru.mail.polis.open.task9;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;

public class RssToFile {

    public static void transfer(URL rssSource, OutputStreamWriter writer) throws FeedException, IOException {

        transfer(new XmlReader(rssSource), writer);
    }

    static void transfer(Reader reader, OutputStreamWriter writer) throws FeedException, IOException {

        SyndFeedInput input = new SyndFeedInput();

        SyndFeed syndFeed = input.build(reader);

        for (SyndEntry entry : syndFeed.getEntries()) {
            writer.write(entry.getTitle() + "\n");
            writer.write(entry.getDescription().getValue() + "\n");
            writer.write(entry.getLink() + "\n");
            writer.write(entry.getPublishedDate() + "\n");
            writer.write("----------------------------------------\n");
        }
    }
}
