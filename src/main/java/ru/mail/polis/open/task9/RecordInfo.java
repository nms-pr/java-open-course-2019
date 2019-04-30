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

public class RecordInfo {

    private URL url;
    private File file;
    private SyndFeed feed;

    public RecordInfo(String link, String file) throws MalformedURLException {
        if (link == null) {
            throw new IllegalArgumentException("URL can't be null");
        }
        if (file == null) {
            throw new IllegalArgumentException("File can't be null");
        }
        this.url = new URL(link);
        this.file = new File(file);
    }

    public void setup() throws IOException, FeedException {
        SyndFeedInput inputfeed = new SyndFeedInput();
        feed = inputfeed.build(new XmlReader(url));
    }

    public void recordingToTheFile() {
        try {
            setup();
        } catch (IOException | FeedException e) {
            e.getStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(file);
            for (SyndEntry entry : feed.getEntries()) {
                if (entry.getTitle() != null) {
                    writer.write(entry.getTitle() + "\n");
                } else {
                    writer.write("title: null" + "\n");
                }
                if (entry.getDescription() != null) {
                    writer.write(entry.getDescription() + "\n");
                } else {
                    writer.write("description: null" + "\n");
                }
                if (entry.getPublishedDate() != null) {
                    writer.write(entry.getPublishedDate() + "\n");
                } else {
                    writer.write("date: null" + "\n");
                }
                writer.write(entry.getLink() + "\n");
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
